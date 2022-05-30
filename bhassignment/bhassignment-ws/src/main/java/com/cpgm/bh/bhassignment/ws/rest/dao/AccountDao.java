package com.cpgm.bh.bhassignment.ws.rest.dao;

import java.util.List;

import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;
import com.cpgm.bh.bhassignment.jpa.customer.Transaction;

public interface AccountDao {

	CustomerAccount getAccountById(String accountId);
	
	void createAccount(CustomerAccount account);
	
	CustomerAccount save(CustomerAccount account);
	
	void createTransaction(Transaction transaction);

	Long countAccountTransactions(String accountId);

	List<Transaction> getAccountTransactions(String accountId, int pageNumber, int pageSize);

}
