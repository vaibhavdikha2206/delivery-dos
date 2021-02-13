package io.delivery.dos.utils;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import io.delivery.dos.models.razorpay.GeneratedOrder;
import io.delivery.dos.models.razorpay.RazorPayNotes;
import io.delivery.dos.models.security.KeyObject;
import io.delivery.dos.models.security.SecretObject;

@Component
public class RazorPayUtil {

	private RazorpayClient client;
	
	@Autowired
    public RazorPayUtil(KeyObject key, SecretObject secret) throws RazorpayException {
		client = new RazorpayClient(key.getKeyString(), secret.getSecretKey());
    }
	
	public GeneratedOrder generateOrderId(int amountInPaisa, String receipt,Map<String, String> map) throws RazorpayException, JSONException {
		
		JSONObject options = new JSONObject();
		options.put("amount", amountInPaisa);
		options.put("currency", "INR");
		options.put("receipt", receipt);
		options.put("payment_capture", 1);
		options.put("notes", map);
		
		Order order = client.Orders.create(options);
		// Now do the magic.
		GeneratedOrder generatedOrder = new Gson().fromJson(order.toJson().toString(), GeneratedOrder.class);
		return generatedOrder;
	}

	public Boolean confirmPaymentStatus(String orderid) throws RazorpayException, JSONException {
		
		Order confirmationOrder = client.Orders.fetch(orderid);
		// Now do the magic.
	
		System.out.println("confirmed order "+confirmationOrder);
		if(confirmationOrder.get("status").equals("captured")) {
			System.out.println("Payment Captured");
			return true ;
		}
		return false ;
	}

	public int convertPaisaToRs(int paisaAmount) {
		return (paisaAmount/100);
	}
}
