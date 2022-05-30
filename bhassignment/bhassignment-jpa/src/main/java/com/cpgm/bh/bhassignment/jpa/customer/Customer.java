package com.cpgm.bh.bhassignment.jpa.customer;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@Column(name="CUSTID", length=25)
	private String customerId;
	
	@Column(name="CUSTLNAM", nullable = false)
	private String customerLastName;
	
	@Column(name="CUSTFNAM", nullable = false)
	private String customerFirstName;
	
	@Column(name="CUSTADDR")
	private String address;
	
	@Column(name="CUSTMAIL", length=30)
	private String customerEmail;
	
	@Column(name="CUSTTEL", length=30)
	private String customerPhone;
	
	@Column(name="REGDAT", nullable = false)
	private Date registrationDate;
	
	public Customer() {
		
	}

	public Customer(String customerLastName, String customerFirstName, String address, String customerEmail,
			String customerPhone) {
		super();
		this.customerLastName = customerLastName;
		this.customerFirstName = customerFirstName;
		this.address = address;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	@PrePersist
	public void setDefaultValues() {
		setRegistrationDate(Calendar.getInstance().getTime());
	}
}
