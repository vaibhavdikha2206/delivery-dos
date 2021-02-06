package io.delivery.dos.service.fcm.model;

import java.util.Map;

import lombok.Data;

@Data
public class Note {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String image;
    
    public Note() {}
    
	public Note(String subject, String content, Map<String, String> data, String image) {
		super();
		this.subject = subject;
		this.content = content;
		this.data = data;
		this.image = image;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
    
    
}