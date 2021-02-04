package io.delivery.dos.models.razorpay;


public class GenerateOrderObject {

	private long amount;
	private String currency;
	private String receipt;
	private long paymentCapture;
	private RazorPayNotes notes;


	public long getAmount() { return amount; }

	public void setAmount(long value) { this.amount = value; }


	public String getCurrency() { return currency; }

	public void setCurrency(String value) { this.currency = value; }


	public String getReceipt() { return receipt; }
	public void setReceipt(String value) { this.receipt = value; }


	public long getPaymentCapture() { return paymentCapture; }
	public void setPaymentCapture(long value) { this.paymentCapture = value; }


	public RazorPayNotes getNotes() { return notes; }
	public void setNotes(RazorPayNotes value) { this.notes = value; }

	public GenerateOrderObject(long amount, String currency, String receipt, long paymentCapture,
			RazorPayNotes notes) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.receipt = receipt;
		this.paymentCapture = paymentCapture;
		this.notes = notes;
	}

	public GenerateOrderObject() {}

}
