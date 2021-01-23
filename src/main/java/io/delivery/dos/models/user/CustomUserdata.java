package io.delivery.dos.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class CustomUserdata {

	@Id
	@Column(name = "surname")
	private String surname;

	public CustomUserdata() {

	}

	public CustomUserdata(String surname) {
		super();
		this.surname = surname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
