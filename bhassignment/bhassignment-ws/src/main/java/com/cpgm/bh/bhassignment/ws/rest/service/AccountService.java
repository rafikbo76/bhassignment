package com.cpgm.bh.bhassignment.ws.rest.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;
import com.cpgm.bh.bhassignment.jpa.customer.Transaction;

import javassist.NotFoundException;

public interface AccountService {

	@Transactional
	CustomerAccount createAccount(String customerId, BigDecimal initialBalance) throws NotFoundException;
	
	@Transactional
	CustomerAccount saveAccount(CustomerAccount account);
	
	@Transactional
	BigDecimal creditAccount(String accountId, BigDecimal credit) throws NotFoundException;
	
	@Transactional
	BigDecimal debitAccount(String accountId, BigDecimal debit) throws NotFoundException;
	
	long countAccountTransactions(String accountId);
	
	List<Transaction> getAccountTransactions(String accountId, int pageNumber, int pageSize);

}
