package io.delivery.dos.vendorDeliveryControllers;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.address.Address;
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.delivery.availability.AvailabilityRequest;
import io.delivery.dos.models.delivery.availability.AvailabilityResponse;
import io.delivery.dos.models.delivery.savedelivery.SaveDeliveryRequestObject;
import io.delivery.dos.models.delivery.savedelivery.SaveDeliveryResponse;
import io.delivery.dos.models.initiatedelivery.InitiateDeliveryRequestObject;
import io.delivery.dos.models.initiatedelivery.InitiateDeliveryResponseObject;
import io.delivery.dos.models.razorpay.GeneratedOrder;
import io.delivery.dos.models.razorpay.RazorPayNotes;
import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.repositories.user.ProfileRepository;
import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.service.fcm.model.Note;
import io.delivery.dos.utils.AddressUtil;
import io.delivery.dos.utils.DeliveryStatusUtil;
import io.delivery.dos.utils.MapsUtil;
import io.delivery.dos.utils.NotifUtil;
import io.delivery.dos.utils.RazorPayUtil;

@RestController
public class SaveDeliveryController {

	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	@Autowired
	private RazorPayUtil razorPayUtil;
	
	@Autowired
	private MapsUtil mapsUtil;
	
	@Autowired
	private AddressUtil addressUtil;
	
	@Autowired
	private NotifUtil notifUtil;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private DeliveryStatusUtil deliveryStatusUtil;
	
	@RequestMapping(method=RequestMethod.POST,value="/deliveryRequest")
	public SaveDeliveryResponse deliveryRequest(@RequestBody Deliveries saveDeliveryRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) throws Exception { 
	
		// first save it in db with status as Payment_Awaiting
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        
        Address originAddress = addressUtil.checkIfAddressCorrespondsToUser(userid, saveDeliveryRequestObject.getOriginaddressid());
        System.out.println("deliveryaddress verified for "+userid+","+saveDeliveryRequestObject.getOriginaddressid());
        if(originAddress !=null) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "key1 value");
        map.put("key2", "key2 value");
      
        System.out.println("1");
        int amountInPaisa = mapsUtil.getAmountFromDistanceInPaisa(originAddress,saveDeliveryRequestObject.getDroplatitude(),saveDeliveryRequestObject.getDroplongitude());
        System.out.println("2");
        GeneratedOrder generatedOrder = razorPayUtil.generateOrderId(amountInPaisa,"reciept for "+userid,map);   
		
        Deliveries recvdDelivery = new Deliveries(null,userid,saveDeliveryRequestObject.getPickuptime(),
				saveDeliveryRequestObject.getOriginaddressid(),saveDeliveryRequestObject.getDropaddress(),
				saveDeliveryRequestObject.getDroplatitude(),saveDeliveryRequestObject.getDroplongitude(),
				Constants.status_PAYMENT_AWAITING,null,generatedOrder.getID(),razorPayUtil.convertPaisaToRs(generatedOrder.getAmount()));
		
        
		Deliveries savedDelivery=deliveriesRepository.save(recvdDelivery);
		//after save success get order id and send object		
		
		return new SaveDeliveryResponse(razorPayUtil.convertPaisaToRs(generatedOrder.getAmount()),generatedOrder.getID(),savedDelivery.getDeliveryid()) ; 
        }
        
        else throw new Exception("Incorrect addressid"); 
      }
	
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST,value="/initiateDelivery")
	public InitiateDeliveryResponseObject initiateDelivery(@RequestBody InitiateDeliveryRequestObject initiateDeliveryRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) throws Exception { 
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);  
        System.out.println("id is "+initiateDeliveryRequestObject.getDeliveryid());
        System.out.println("orderid is "+initiateDeliveryRequestObject.getOrderid());
        System.out.println("user is "+userid);
        Deliveries delivery = deliveriesRepository.findOneByDeliveryidAndUseridAndOrderid(initiateDeliveryRequestObject.getDeliveryid(),userid,initiateDeliveryRequestObject.getOrderid());
        // first check if delivery id is for the user with correct jwt
        System.out.println("delivery is "+delivery.getOrderid());
        if(delivery!=null) {
        	//then confirm payment status first	
        	if(razorPayUtil.confirmPaymentStatus(delivery.getOrderid())) {	
        		//update status , send notification
        		System.out.println("payment confirmed not null");
        		deliveryStatusUtil.updateDeliveryStatus(delivery.getDeliveryid(), Constants.delivery_status_Delivery_Scheduling);
        		
        		if(initiateDeliveryRequestObject.getRazorhash()!=null)
        		deliveryStatusUtil.updateDeliveryPaymentHash(delivery.getDeliveryid(), initiateDeliveryRequestObject.getRazorhash(),initiateDeliveryRequestObject.getRazorsignature(),initiateDeliveryRequestObject.getRazorpayid());
        	
        		String usertoken = profileRepository.findByUseridCustom(userid).getToken();
        		if(usertoken!=null) {
        			sendNotificationToUserForScheduling(userid,delivery);
        		}
        		
        		// now trigger notif to free riders
        		
        		notifUtil.sendNotificationToFreeRiders(delivery);
        		
        		return new InitiateDeliveryResponseObject(delivery.getDeliveryid(),Constants.delivery_status_Delivery_Scheduling);
        		
        	}
        }
     
        System.out.println("Seedha Throw");
        throw new Exception("Unable To Initiate Delivery");
		
	}
	
	private void sendNotificationToUserForScheduling(String userid,Deliveries delivery) throws FirebaseMessagingException {
		
		String usertoken = profileRepository.findByUseridCustom(userid).getToken();
		if(usertoken!=null) {
    		Map<String, String> notemap = new HashMap<String, String>();
    		notemap.put("deliveryId", delivery.getDeliveryid().toString());
    		notemap.put("type", delivery.getStatus());
    		notemap.put("pickuptime", delivery.getPickuptime());
            
    		Note note = new Note(Constants.delivery_scheduling_notification_title_string,String.format(Constants.delivery_scheduling_notification_description_string, delivery.getPickuptime()),notemap,null);
    		
    		System.out.println("sending single notif to "+usertoken);
    		notifUtil.sendNotificationToUser(note, usertoken);
		}
	}
}
