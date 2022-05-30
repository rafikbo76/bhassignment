package com.cpgm.bh.bhassignment.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.cpgm.bh.bhassignment.jpa.customer.Customer;

@XmlRootElement
public class CustomerDto {
	
	private String customerId;
	private String customerLastName;
	private String customerFirstName;
	private String address;
	private String customerEmail;
	private String customerPhone;
	private Date registrationDate;
	
	public CustomerDto() {
		
	}

	public CustomerDto(Customer customer) {
		this.customerId = customer.getCustomerId();
		this.customerLastName = customer.getCustomerLastName();
		this.customerFirstName = customer.getCustomerFirstName();
		this.address = customer.getAddress();
		this.customerEmail = customer.getCustomerEmail();
		this.customerPhone = customer.getCustomerPhone();
		this.registrationDate = customer.getRegistrationDate();
	}

	public CustomerDto(String customerId, String customerLastName, String customerFirstName, String address,
			String customerEmail, String customerPhone) {
		super();
		this.customerId = customerId;
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
}
