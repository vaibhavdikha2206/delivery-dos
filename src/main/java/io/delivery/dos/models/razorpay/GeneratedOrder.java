package io.delivery.dos.models.razorpay;

public class GeneratedOrder {

	private long amount;
    private long amountPaid;
    private Object[] notes;
    private long createdAt;
    private long amountDue;
    private String currency;
    private String receipt;
    private String id;
    private String entity;
    private Object offerID;
    private String status;
    private long attempts;

    public GeneratedOrder() {}
    
    
    
    public GeneratedOrder(long amount, long amountPaid, Object[] notes, long createdAt, long amountDue,
			String currency, String receipt, String id, String entity, Object offerID, String status, long attempts) {
		super();
		this.amount = amount;
		this.amountPaid = amountPaid;
		this.notes = notes;
		this.createdAt = createdAt;
		this.amountDue = amountDue;
		this.currency = currency;
		this.receipt = receipt;
		this.id = id;
		this.entity = entity;
		this.offerID = offerID;
		this.status = status;
		this.attempts = attempts;
	}



	public long getAmount() { return amount; }
  
    public void setAmount(long value) { this.amount = value; }

  
    public long getAmountPaid() { return amountPaid; }
   
    public void setAmountPaid(long value) { this.amountPaid = value; }

   
    public Object[] getNotes() { return notes; }
    
    public void setNotes(Object[] value) { this.notes = value; }

    
    public long getCreatedAt() { return createdAt; }
    
    public void setCreatedAt(long value) { this.createdAt = value; }

   
    public long getAmountDue() { return amountDue; }
    
    public void setAmountDue(long value) { this.amountDue = value; }

   
    public String getCurrency() { return currency; }
    
    public void setCurrency(String value) { this.currency = value; }

   
    public String getReceipt() { return receipt; }
   
    public void setReceipt(String value) { this.receipt = value; }

   
    public String getID() { return id; }
    
    public void setID(String value) { this.id = value; }

    
    public String getEntity() { return entity; }
   
    public void setEntity(String value) { this.entity = value; }

    
    public Object getOfferID() { return offerID; }
   
    public void setOfferID(Object value) { this.offerID = value; }

    
    public String getStatus() { return status; }
   
    public void setStatus(String value) { this.status = value; }

    
    public long getAttempts() { return attempts; }
   
    public void setAttempts(long value) { this.attempts = value; }
    
}
