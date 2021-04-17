package io.delivery.dos.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.paytm.pg.merchant.PaytmChecksum;
import com.razorpay.Payment;
import com.razorpay.RazorpayException;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.paytm.GeneratedOrderPaytm;

@Component
public class PaytmUtil {

	public GeneratedOrderPaytm generateOrderId(int amountInRs,String customerId,String orderId) throws Exception {
		
		System.out.println("orderid "+orderId);
		
		JSONObject paytmParams = new JSONObject();

		JSONObject body = new JSONObject();
		body.put("requestType", "Payment");
		//body.put("mid", "KaUwui76667909288501"); //test
		body.put("mid", "xgoisa93682054870842"); //live
		//body.put("websiteName", "WEBSTAGING"); //test
		body.put("websiteName", "SIMPLIFIED"); //live
		body.put("orderId", orderId);
		//body.put("callbackUrl", String.format("https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=%s",orderId));  // test
		body.put("callbackUrl", String.format("https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=%s",orderId)); //live
		
		JSONObject txnAmount = new JSONObject();
		txnAmount.put("value",amountInRs);
		txnAmount.put("currency", "INR");

		JSONObject userInfo = new JSONObject();
		userInfo.put("custId", customerId);
		body.put("txnAmount", txnAmount);
		body.put("userInfo", userInfo);

		// String checksum = PaytmChecksum.generateSignature(body.toString(), "gXqM_dy7Z@U6o85J"); //test
		String checksum = PaytmChecksum.generateSignature(body.toString(), "#GY_jNcyoizFVSSp@U6o85J"); //live

		JSONObject head = new JSONObject();
		head.put("signature", checksum);

		paytmParams.put("body", body);
		paytmParams.put("head", head);
		String post_data = paytmParams.toString();

		//URL url = new URL(String.format("https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction?mid=%s&orderId=%s", "KaUwui76667909288501",orderId)); //test
		URL url = new URL(String.format("https://securegw.paytm.in/theia/api/v1/initiateTransaction?mid=%s&orderId=%s", "xgoisa93682054870842",orderId)); //live
		

		try {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);

		DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
		requestWriter.writeBytes(post_data);
		requestWriter.close();
		String responseData = "";
		InputStream is = connection.getInputStream();
		BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
		if ((responseData = responseReader.readLine()) != null) {
		System.out.println("Response: " + responseData);
		Gson g = new Gson();  
		GeneratedOrderPaytm generatedOrderPaytm = g.fromJson(responseData, GeneratedOrderPaytm.class) ;
		return generatedOrderPaytm;
		}
		responseReader.close();
		} catch (Exception exception) {
		exception.printStackTrace();
		}

		throw new Exception("unable to generate Paytm Orderid");
	}
	
	public Boolean confirmPaytmPaymentStatus(String orderid) throws Exception {
		
		/* initialize an object */
		JSONObject paytmParams = new JSONObject();

		/* body parameters */
		JSONObject body = new JSONObject();

		/* Find your MID in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */
		// body.put("mid", "KaUwui76667909288501"); //test
		body.put("mid", "xgoisa93682054870842");	//live

		/* Enter your order id which needs to be check status for */
		body.put("orderId", orderid);

		/**
		* Generate checksum by parameters we have in body
		* You can get Checksum JAR from https://developer.paytm.com/docs/checksum/
		* Find your Merchant Key in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys 
		*/
		
		String checksum = PaytmChecksum.generateSignature(body.toString(), "#GY_jNcyoizFVSSp");
		/* head parameters */
		JSONObject head = new JSONObject();

		/* put generated checksum value here */
		head.put("signature", checksum);

		/* prepare JSON string for request */
		paytmParams.put("body", body);
		paytmParams.put("head", head);
		String post_data = paytmParams.toString();

		/* for Staging */
		//URL url = new URL("https://securegw-stage.paytm.in/v3/order/status");

		/* for Production */
		URL url = new URL("https://securegw.paytm.in/v3/order/status");

		try {
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json");
		    connection.setDoOutput(true);

		    DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
		    requestWriter.writeBytes(post_data);
		    requestWriter.close();
		    String responseData = "";
		    InputStream is = connection.getInputStream();
		    BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
		    if ((responseData = responseReader.readLine()) != null) {
		        System.out.println("Response: " + responseData);
		    }
		    // System.out.append("Request: " + post_data);
		    Gson g = new Gson();  
			GeneratedOrderPaytm generatedOrderPaytm = g.fromJson(responseData, GeneratedOrderPaytm.class) ;
		    responseReader.close();
		    
		    if(generatedOrderPaytm.getBody().getResultInfo().getResultCode().equals(Constants.capturedStatusCodePaytm) && 
					generatedOrderPaytm.getBody().getResultInfo().getResultStatus().equals(Constants.capturedStatusPaytm)) {
				System.out.println("Paytm Payment Captured");
				return true ;
			}
		    System.out.println("Paytm Payment Not Captured");
			return false ;
		} catch (Exception exception) {
		    exception.printStackTrace();
		}

		return false;

	}
}
