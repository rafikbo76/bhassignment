package com.cpgm.bh.bhassignment.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.cpgm.bh.bhassignment.jpa.customer.Transaction;
import com.cpgm.bh.bhassignment.jpa.customer.TransactionType;

public class TransactionDto {
	
	private String transactionId;
	private String accountId;
	private String customerId;
	private String customerFirstName;
	private String customerLastName;
	private TransactionType type;
	private BigDecimal amount;
	private Date registrationDate;
	
	public TransactionDto() {
		
	}
	
	public TransactionDto(Transaction transaction) {
		this.transactionId = transaction.getTransactionId();
		if (transaction.getAccount()!=null) {
			this.accountId = transaction.getAccount().getAccountId();
			if (transaction.getAccount().getCustomer()!=null) {
				this.customerId = transaction.getAccount().getCustomer().getCustomerId();
				this.customerFirstName = transaction.getAccount().getCustomer().getCustomerFirstName();
				this.customerLastName = transaction.getAccount().getCustomer().getCustomerLastName();
			}
		}
		
		this.type = transaction.getType();
		this.amount = transaction.getAmount();
		this.registrationDate = transaction.getRegistrationDate();
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
