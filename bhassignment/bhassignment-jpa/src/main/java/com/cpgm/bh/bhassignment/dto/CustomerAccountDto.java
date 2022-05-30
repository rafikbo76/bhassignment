package com.cpgm.bh.bhassignment.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;

@XmlRootElement
public class CustomerAccountDto {

	private String accountId;
	private String customerId;
	private String customerFirstName;
	private String customerLastName;
	private BigDecimal balance;
	private Date registrationDate;
	
	public CustomerAccountDto() {
		
	}
	
	public CustomerAccountDto(CustomerAccount account) {
		this.accountId = account.getAccountId();
		if (account.getCustomer()!=null) {
			this.customerId = account.getCustomer().getCustomerId();
			this.customerFirstName = account.getCustomer().getCustomerFirstName();
			this.customerLastName = account.getCustomer().getCustomerLastName();
		}
		this.balance = account.getBalance();
		this.registrationDate = account.getRegistrationDate();
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
