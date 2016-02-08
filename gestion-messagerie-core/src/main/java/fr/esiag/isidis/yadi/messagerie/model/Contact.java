package fr.esiag.isidis.yadi.messagerie.model;

import java.io.Serializable;

public class Contact implements Serializable {

	private static final long serialVersionUID = -1802268290784123950L;
	private Integer id;
	private String firstName;
	private String LastName;
	private String login;
	private String password;

	public Contact() {
		this.id = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
