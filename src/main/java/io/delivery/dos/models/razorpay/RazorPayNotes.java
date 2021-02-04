package io.delivery.dos.models.razorpay;

public class RazorPayNotes {

	private String notesKey1;
	private String notesKey2;


	public String getNotesKey1() { return notesKey1; }
	
	public void setNotesKey1(String value) { this.notesKey1 = value; }


	public String getNotesKey2() { return notesKey2; }
	public void setNotesKey2(String value) { this.notesKey2 = value; }

	public RazorPayNotes(String notesKey1, String notesKey2) {
		super();
		this.notesKey1 = notesKey1;
		this.notesKey2 = notesKey2;
	}

	public RazorPayNotes() {}
	
}
