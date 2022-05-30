package com.cpgm.bh.bhassignment.jpa.customer;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@Column(name="TRANSID", length=25)
	private String transactionId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ACCID", nullable = false)
	private CustomerAccount account;
	
	@Column(name="TRANTYP")
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@Column(name="TRANAM", nullable = false)
	private BigDecimal amount;
	
	@Column(name="REGDAT", nullable = false)
	private Date registrationDate;
	
	public Transaction() {
		
	}

	public Transaction(String transactionId, CustomerAccount account, TransactionType type, BigDecimal amount) {
		super();
		this.transactionId = transactionId;
		this.account = account;
		this.type = type;
		this.amount = amount;
	}

	public CustomerAccount getAccount() {
		return account;
	}

	public void setAccount(CustomerAccount account) {
		this.account = account;
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

	public String getTransactionId() {
		return transactionId;
	}
	
	@PrePersist
	public void setDefaultValues() {
		setRegistrationDate(Calendar.getInstance().getTime());
	}
}
