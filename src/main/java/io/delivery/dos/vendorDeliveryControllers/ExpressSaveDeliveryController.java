package io.delivery.dos.vendorDeliveryControllers;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import io.delivery.dos.models.expressdelivery.ExpressDeliveries;
import io.delivery.dos.models.initiatedelivery.InitiateDeliveryRequestObject;
import io.delivery.dos.models.initiatedelivery.InitiateDeliveryResponseObject;
import io.delivery.dos.models.paytm.GeneratedOrderPaytm;
import io.delivery.dos.models.razorpay.GeneratedOrder;
import io.delivery.dos.models.razorpay.RazorPayNotes;
import io.delivery.dos.models.user.ProfileWithReferralCode;
import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.repositories.expressdelivery.ExpressDeliveriesRepository;
import io.delivery.dos.repositories.user.ProfileRepository;
import io.delivery.dos.repositories.user.ProfileRepositoryWithReferralCode;
import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.service.fcm.model.Note;
import io.delivery.dos.utils.AddressUtil;
import io.delivery.dos.utils.DeliveryStatusUtil;
import io.delivery.dos.utils.MapsUtil;
import io.delivery.dos.utils.NotifUtil;
import io.delivery.dos.utils.PaytmUtil;
import io.delivery.dos.utils.RazorPayUtil;

@RestController
public class ExpressSaveDeliveryController {

	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	@Autowired
	private RazorPayUtil razorPayUtil;
	
	@Autowired
	private PaytmUtil paytmUtil;
	
	@Autowired
	private MapsUtil mapsUtil;
	
	@Autowired
	private AddressUtil addressUtil;
	
	@Autowired
	private NotifUtil notifUtil;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private ProfileRepositoryWithReferralCode profileRepositoryWithReferralCode;
	
	@Autowired
	private DeliveryStatusUtil deliveryStatusUtil;
	
	private String getCurrentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");  
		   LocalDateTime now = LocalDateTime.now().plusMinutes(330);  
		   System.out.println("Current Time is "+dtf.format(now));
		   return dtf.format(now);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/expressDeliveryRequest")
	public SaveDeliveryResponse expressDeliveryRequest(@RequestBody Deliveries saveDeliveryRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) throws Exception { 
	
		// first save it in db with status as Payment_Awaiting
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        
        Address originAddress = addressUtil.checkIfAddressCorrespondsToUser(userid, saveDeliveryRequestObject.getOriginaddressid());
         
        if(originAddress !=null) {
      
        int totalAmountInPaisa = mapsUtil.getExpressAmountFromParamsInPaisa(originAddress,saveDeliveryRequestObject.getDroplatitude(),saveDeliveryRequestObject.getDroplongitude(),saveDeliveryRequestObject.getWeightcategory(),
        		saveDeliveryRequestObject.getIsDelicate());

        Deliveries recvdDelivery = getExpressSaveDeliveryObject(saveDeliveryRequestObject,userid,totalAmountInPaisa,saveDeliveryRequestObject.getCreditsused());

        Deliveries savedDelivery=deliveriesRepository.save(recvdDelivery);
		//after save success get order id and send object		
		
		return new SaveDeliveryResponse(savedDelivery.getDeliverycharge(),savedDelivery.getOrderid(),savedDelivery.getDeliveryid(),savedDelivery.getPaytmTxnToken(),getExpressPaymentMethod(savedDelivery.getPaymentMethod())) ; 
        }
        
        else throw new Exception("Incorrect addressid"); 
      }
	
		private Deliveries getExpressSaveDeliveryObject(Deliveries saveDeliveryRequestObject, String userid, int totalAmountInPaisa,int creditsUsed)
				throws Exception {
			
			switch (getExpressPaymentMethod(saveDeliveryRequestObject.getPaymentMethod())) {
			case Constants.paymentMethodPayTM: {
				// PAYTM ORDER GENERATION
				System.out.println("PaytmTime");
				String paytmOrderId = String.format("%s_%s_%s", Constants.paytmOrderIdText, userid, getCurrentTime());
				
				
				int payableDeliveryAmountInRs = getPayableDeliveryCharge(razorPayUtil.convertPaisaToRs(totalAmountInPaisa),creditsUsed);
				
				int payableDeliveryAmountInPaisa = razorPayUtil.convertRsToPaisa(payableDeliveryAmountInRs);
				// check if user has valid credits to be used 
				if(checkCreditsValidity(userid,saveDeliveryRequestObject.getCreditsused())) {
				GeneratedOrderPaytm generatedOrderPaytm = paytmUtil
						.generateOrderId(razorPayUtil.convertPaisaToRs(payableDeliveryAmountInPaisa), userid, paytmOrderId);

				return getExpressDeliveryObject(saveDeliveryRequestObject, userid,
						razorPayUtil.convertPaisaToRs(payableDeliveryAmountInPaisa),totalAmountInPaisa, paytmOrderId,
						generatedOrderPaytm.getBody().getTxnToken());
				
				}
				
				 else throw new Exception("Request Unsuccessful , Please try again with Correct Selections"); 

			}

			default: {
				// RAZORPAY ORDER GENERATION
				System.out.println("RazorPayTime");
				
				int payableDeliveryAmountInRs = getPayableDeliveryCharge(razorPayUtil.convertPaisaToRs(totalAmountInPaisa),creditsUsed);
				int payableDeliveryAmountInPaisa = razorPayUtil.convertRsToPaisa(payableDeliveryAmountInRs);
				
				//convert totalAmountInPaisa to rs get deliveryAmountInRs and then convert it back to Paisa
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("amount", payableDeliveryAmountInPaisa + "");
				if(checkCreditsValidity(userid,saveDeliveryRequestObject.getCreditsused())) {
				GeneratedOrder generatedOrder = razorPayUtil.generateOrderId(payableDeliveryAmountInPaisa, "reciept for " + userid,
						map);
				return getExpressDeliveryObject(saveDeliveryRequestObject, userid,
						razorPayUtil.convertPaisaToRs(generatedOrder.getAmount()),totalAmountInPaisa, generatedOrder.getID(), null);
				}
				 else throw new Exception("Request Unsuccessful , Please try again with Correct Selections"); 

			}
			
		}
		}

		private boolean checkCreditsValidity(String userid,int creditsUsed) {
			// check if the said profile is having enough credits or not
			ProfileWithReferralCode profileObject = profileRepositoryWithReferralCode.findByUseridCustom(userid);
			if(profileObject.getCredits()>=creditsUsed) {
				return true;
			}
			return false;
		}

		private Deliveries getExpressDeliveryObject(Deliveries saveDeliveryRequestObject,String userid,int payableAmountInRs, int totalAmountInPaisa,String orderid,String paytmTxnToken) {
			System.out.println("----------------------------------");
			return new Deliveries(null,userid,saveDeliveryRequestObject.getPickuptime(),
	 				saveDeliveryRequestObject.getOriginaddressid(),saveDeliveryRequestObject.getDropaddress(),
	 				saveDeliveryRequestObject.getDroplatitude(),saveDeliveryRequestObject.getDroplongitude(),
	 				Constants.status_PAYMENT_AWAITING,null,orderid,payableAmountInRs,
	 				saveDeliveryRequestObject.getDescription(),saveDeliveryRequestObject.getImg(),saveDeliveryRequestObject.getWeightcategory(),saveDeliveryRequestObject.getDestinationcontact(),
	 				saveDeliveryRequestObject.getIsDelicate(),saveDeliveryRequestObject.getIsBalloonAdded(),saveDeliveryRequestObject.getIsBouqetAdded(),saveDeliveryRequestObject.getIsTwoCakes(),getExpressPaymentMethod(saveDeliveryRequestObject.getPaymentMethod()),paytmTxnToken,
	 				razorPayUtil.convertPaisaToRs(totalAmountInPaisa),saveDeliveryRequestObject.getCreditsused());
		}
		
		private int getPayableDeliveryCharge(int totalDeliveryCharge,int creditsUsed) throws Exception {
			if((totalDeliveryCharge - creditsUsed)>=0) {
				return totalDeliveryCharge - creditsUsed ; 
			}
			throw new Exception("Request Unsuccessful , Please try again with Correct Selections");  
		}
		
		private String getExpressPaymentMethod(String paymentMethod) {
			System.out.println("payemnt method " + paymentMethod);
			if (paymentMethod == null || (!paymentMethod.equals(Constants.paymentMethodPayTM))) {
				System.out.println("returning  " + Constants.paymentMethodRazorPay);
				return Constants.paymentMethodRazorPay;
			}
			System.out.println("returning  " + Constants.paymentMethodPayTM);
			return Constants.paymentMethodPayTM;
		}
		
		
		
		@Transactional
		@RequestMapping(method=RequestMethod.POST,value="/initiateExpressDelivery")
		public InitiateDeliveryResponseObject initiateExpressDelivery(@RequestBody InitiateDeliveryRequestObject initiateDeliveryRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) throws Exception { 
			String jwt = authorizationHeader.substring(7);
	        String userid = jwtUtil.extractUsername(jwt);  
	        System.out.println("id is "+initiateDeliveryRequestObject.getDeliveryid());
	        System.out.println("orderid is "+initiateDeliveryRequestObject.getOrderid());
	        System.out.println("user is "+userid);
	        Deliveries delivery = deliveriesRepository.findOneByDeliveryidAndUseridAndOrderid(initiateDeliveryRequestObject.getDeliveryid(),userid,initiateDeliveryRequestObject.getOrderid());
	        // first check if delivery id is for the user with correct jwt
	        System.out.println("delivery is "+delivery);
	        System.out.println("delivery is "+delivery.getOrderid());
	        if(delivery!=null) {
	        	//then confirm payment status first	
	        	
	        	if(confirmExpressPaymentStatus(delivery)) {	
	        		//update status , send notification
	        		System.out.println("payment confirmed not null");
	        		deliveryStatusUtil.updateDeliveryStatus(delivery.getDeliveryid(), Constants.delivery_status_Delivery_Scheduling);
	        		
	        		String usertoken = profileRepository.findByUseridCustom(userid).getToken();
	        		
	        		// for express : ALSO UPDATE CREDITS HERE
	        		updateUserCredits(userid,delivery,usertoken);
	        		
	        		if(initiateDeliveryRequestObject.getRazorhash()!=null)
	        		deliveryStatusUtil.updateDeliveryPaymentHash(delivery.getDeliveryid(), initiateDeliveryRequestObject.getRazorhash(),initiateDeliveryRequestObject.getRazorsignature(),initiateDeliveryRequestObject.getRazorpayid());
	        	
	        		
	        		if(usertoken!=null) {
	        			sendExpressNotificationToUserForScheduling(userid,delivery,usertoken,Constants.delivery_status_Delivery_Scheduling);
	        		}
	        		
	        		// now trigger notif to free riders
	        		notifUtil.sendNotificationToFreeRiders(delivery);
	        		
	        		return new InitiateDeliveryResponseObject(delivery.getDeliveryid(),Constants.delivery_status_Delivery_Scheduling);
	        		
	        	}
	        }
	     
	        throw new Exception("Unable To Initiate Delivery");
			
		}
		
		private void updateUserCredits(String userid,Deliveries delivery,String token) throws FirebaseMessagingException {
			
			if(delivery.getCreditsused()>0) {
				profileRepositoryWithReferralCode.updateProfileWithGivenCredits(userid,delivery.getCreditsused());
				sendNotificationToUserForCreditsUpdated(userid,delivery,token,Constants.user_notification_Credits_updated);
			}
    		
		}
		
		private boolean confirmExpressPaymentStatus(Deliveries delivery) throws Exception {
			switch(delivery.getPaymentMethod()) {
			 case Constants.paymentMethodPayTM : {
				 return paytmUtil.confirmPaytmPaymentStatus(delivery.getOrderid());
			 }
			 default : {
				 return razorPayUtil.confirmPaymentStatus(delivery.getOrderid());
			 }
			}
		}
			
		private void sendNotificationToUserForCreditsUpdated(String userid,Deliveries delivery,String usertoken,String status) throws FirebaseMessagingException {
			
			notifUtil.sendNotificationToUserForCreditsUpdated(userid,String.valueOf(delivery.getCreditsused()), usertoken, Constants.delivery_scheduling_notification_description_string);
			notifUtil.sendNotificationToAdminWithDeliveryObject(delivery, status);
		}
	

		private void sendExpressNotificationToUserForScheduling(String userid,Deliveries delivery,String usertoken,String status) throws FirebaseMessagingException {
			
			//String usertoken = profileRepository.findByUseridCustom(userid).getToken();
			if(usertoken!=null) {
	    		Map<String, String> notemap = new HashMap<String, String>();
	    		notemap.put("deliveryId", delivery.getDeliveryid().toString());
	    		notemap.put("type", status);
	    		notemap.put("pickuptime", delivery.getPickuptime());
	    		notemap.put("click_action", Constants.FLUTTER_NOTIF_VALUE_STRING);
	    		
	    		Note note = new Note(Constants.delivery_scheduling_notification_title_string,String.format(Constants.delivery_scheduling_notification_description_string, delivery.getPickuptime()),notemap,null);
	    		
	    		System.out.println("sending single notif to "+usertoken);
	    		notifUtil.sendNotificationToUser(note, usertoken);
	    		notifUtil.sendNotificationToAdminWithDeliveryObject(delivery, status);
			}
		}
	
}