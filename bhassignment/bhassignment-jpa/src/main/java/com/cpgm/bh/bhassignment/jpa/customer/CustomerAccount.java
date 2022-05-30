package com.cpgm.bh.bhassignment.jpa.customer;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "customer_accounts")
public class CustomerAccount {
	@Id
	@Column(name="ACCID", length=25)
	private String accountId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CUSTID", nullable = false)
	private Customer customer;
	
	@Column(name="ACCBAL", nullable = false)
	private BigDecimal balance;
	
	@Column(name="REGDAT", nullable = false)
	private Date registrationDate;
	
	public CustomerAccount() {
		
	}

	public CustomerAccount(Customer customer, BigDecimal balance) {
		super();
		this.customer = customer;
		this.balance = balance;
		this.registrationDate = Calendar.getInstance().getTime();
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	
	@PrePersist
	public void setDefaultValues() {
		setRegistrationDate(Calendar.getInstance().getTime());
		if (balance==null)
			setBalance(BigDecimal.ZERO);
	}
}
