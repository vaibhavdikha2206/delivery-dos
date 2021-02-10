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
        
        Address originAddress = addressUtil.checkIfAddressCorrespondsToUser(userid, saveDeliveryRequestObject.getAddressid());
        if(originAddress !=null) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "key1 value");
        map.put("key2", "key2 value");
      
        int amountInPaisa = mapsUtil.getAmountFromDistanceInPaisa(originAddress,saveDeliveryRequestObject.getDroplatitude(),saveDeliveryRequestObject.getDroplongitude());
        
        GeneratedOrder generatedOrder = razorPayUtil.generateOrderId(amountInPaisa,"reciept for "+userid,map);   
		
        Deliveries recvdDelivery = new Deliveries(null,userid,saveDeliveryRequestObject.getPickuptime(),
				saveDeliveryRequestObject.getAddressid(),saveDeliveryRequestObject.getDropaddress(),
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
        Deliveries delivery = deliveriesRepository.findOneByDeliveryidAndUseridAndOrderid(initiateDeliveryRequestObject.getDeliveryid(),userid,initiateDeliveryRequestObject.getOrderid());
        // first check if delivery id is for the user with correct jwt
        if(delivery!=null) {
        	//then confirm payment status first	
        	if(razorPayUtil.confirmPaymentStatus(delivery.getOrderid())) {	
        		//update status , send notification
        		deliveryStatusUtil.updateDeliveryStatus(delivery.getDeliveryid(), Constants.delivery_status_Delivery_Scheduling);
        		
        		if(initiateDeliveryRequestObject.getRazorhash()!=null)
        		deliveryStatusUtil.updateDeliveryPaymentHash(delivery.getDeliveryid(), initiateDeliveryRequestObject.getRazorhash(),initiateDeliveryRequestObject.getRazorsignature(),initiateDeliveryRequestObject.getRazorpayid());
        	
        		String usertoken = profileRepository.findByUseridCustom(userid).getToken();
        		if(usertoken!=null) {
        			sendNotificationToUser(userid,delivery);
        		}
        		
        		// now trigger notif to free riders
        		notifUtil.sendNotificationToFreeRiders(delivery);
        		
        		return new InitiateDeliveryResponseObject(delivery.getDeliveryid(),Constants.delivery_status_Delivery_Scheduling);
        		
        	}
        }
     
        throw new Exception("Unable To Initiate Delivery");
		
	}
	
	private void sendNotificationToUser(String userid,Deliveries delivery) throws FirebaseMessagingException {
		
		String usertoken = profileRepository.findByUseridCustom(userid).getToken();
		if(usertoken!=null) {
    		Map<String, String> notemap = new HashMap<String, String>();
    		notemap.put(Constants.payment_key_notes_status, "200");
    		notemap.put(Constants.payment_key_notes_payload, Constants.delivery_status_Delivery_Scheduling);
    		notemap.put(Constants.payment_key_notes_deliveryid, delivery.getDeliveryid().toString());
            
    		Note note = new Note(Constants.delivery_scheduled_notification_title_string,String.format(Constants.delivery_scheduled_notification_description_string, delivery.getPickuptime()),notemap,null);
    		
    		System.out.println("sending single notif to "+usertoken);
    		notifUtil.sendNotificationToUser(note, usertoken);
		}
	}
}
