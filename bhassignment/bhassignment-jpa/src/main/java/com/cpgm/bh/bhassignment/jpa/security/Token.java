package com.cpgm.bh.bhassignment.jpa.security;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Token {
	
	@Id
	@Column(name="TOKVAL")
	private String tokenValue;
	
	@OneToOne
	@JoinColumn(name="USERNAME")
	private User user;
	
	@Column(name="REGDAT")
	private Date registrationDate;
	
	@Column(name="STARTDAT")
	private Date startDate;
	
	@Column(name="EXPDAT")
	private Date expireDate;
	
	public Token() {
		
	}

	public Token(String tokenValue, User user,
			Date startDate, Date expireDate) {
		super();
		this.tokenValue = tokenValue;
		this.user = user;
		this.startDate = startDate;
		this.expireDate = expireDate;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	@PrePersist
	public void setDefaultValues() {
		setRegistrationDate(Calendar.getInstance().getTime());
	}
}
