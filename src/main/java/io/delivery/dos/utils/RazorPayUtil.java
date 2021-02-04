package io.delivery.dos.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.razorpay.Order;
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
	
	public GeneratedOrder generateOrderId(long amount, String receipt, long paymentCapture,RazorPayNotes notes) throws RazorpayException, JSONException {
		
		JSONObject options = new JSONObject();
		options.put("amount", amount);
		options.put("currency", "INR");
		options.put("receipt", receipt);
		Order order = client.Orders.create(options);
		System.out.println("generated Order "+order);
		// Now do the magic.
		GeneratedOrder generatedOrder = new Gson().fromJson(order.toJson().toString(), GeneratedOrder.class);
		return generatedOrder;
	}

}
