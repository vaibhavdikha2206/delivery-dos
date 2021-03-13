package io.delivery.dos.models.user.request;

public class SignUpObject {

	private String userid;
	private String name;
	private String password;
	private String role;
	private String email;
	private String signkey;
	
	
	public SignUpObject() {

	}

	
	public SignUpObject(String userid, String name, String password, String role, String email, String signkey) {
		super();
		this.userid = userid;
		this.name = name;
		this.password = password;
		this.role = role;
		this.email = email;
		this.signkey = signkey;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSignkey() {
		return signkey;
	}

	public void setSignkey(String signkey) {
		this.signkey = signkey;
	}

	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}
