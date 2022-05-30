package com.cpgm.bh.bhassignment.jpa.security;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Principal {
	
	@Id
	@Column(name="USERNAME", length=30)
	private String username;
	
	@Column(name="PASSWD")
	private String password;
	
	@Column(name="FIRSTNAME", length=30)
	private String firstname;
	
	@Column(name="LASTNAME", length=30)
	private String lastname;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="TEL")
	private String phoneNumber;
	
	@OneToOne
	@JoinColumn(name="ROLCOD")
	private Role role;
	
	@Column(name="REGDAT")
	private Date registrationDate;
	
	@Column(name="EXPDAT")
	private Date expirationDate;
	
	@Column(name="ACCSTAT")
	@Enumerated(EnumType.STRING)
	private AccountStatus status;
	
	public User() {

	}

	public User(String username, String password, String firstname, String lastname, String email, String phoneNumber,
			Role role, AccountStatus status) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	
	@PrePersist
	public void setDefaultValues() {
		setStatus(AccountStatus.ENABLED);
		setRegistrationDate(Calendar.getInstance().getTime());
	}

	@Override
	public String getName() {
		return username;
	}
}
