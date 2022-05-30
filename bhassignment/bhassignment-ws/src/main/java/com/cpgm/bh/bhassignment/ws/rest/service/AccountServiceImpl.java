package com.cpgm.bh.bhassignment.ws.rest.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpgm.bh.bhassignment.jpa.customer.Customer;
import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;
import com.cpgm.bh.bhassignment.jpa.customer.Transaction;
import com.cpgm.bh.bhassignment.jpa.customer.TransactionType;
import com.cpgm.bh.bhassignment.ws.rest.dao.AccountDao;
import com.cpgm.bh.bhassignment.ws.rest.dao.CommonDao;

import javassist.NotFoundException;

public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CommonDao commonDao;

	@Override
	public CustomerAccount createAccount(String customerId, BigDecimal initialBalance) throws NotFoundException {
		Customer customer = customerService.getCustomerById(customerId);
		if (customer==null)
			throw new NotFoundException("Customer not Found");
		
		CustomerAccount account = new CustomerAccount(customer, BigDecimal.ZERO);
		String accountId = commonDao.generateNewEntityCode(CustomerAccount.class, "accountId", "A", 12);
		account.setAccountId(accountId);
		
		accountDao.createAccount(account);
		
		System.out.println("Initial Balance for Account "+accountId +" "+initialBalance);
		
		if ((initialBalance!=null)
			&&(initialBalance.compareTo(BigDecimal.ZERO)>0))
			creditAccount(account.getAccountId(), initialBalance);
		
		return account;
	}

	@Override
	public CustomerAccount saveAccount(CustomerAccount account) {
		return accountDao.save(account);
	}

	@Override
	public BigDecimal creditAccount(String accountId, BigDecimal credit) throws NotFoundException {
		CustomerAccount account = accountDao.getAccountById(accountId);
		if (account==null)
			throw new NotFoundException("Customer Account not Found");
		
		return updateBalance(account, credit, TransactionType.CREDIT);
	}

	@Override
	public BigDecimal debitAccount(String accountId, BigDecimal debit) throws NotFoundException {
		CustomerAccount account = accountDao.getAccountById(accountId);
		if (account==null)
			throw new NotFoundException("Customer Account not Found");
		
		return updateBalance(account, debit, TransactionType.DEBIT);
	}
	
	private BigDecimal updateBalance(CustomerAccount account, BigDecimal amount, TransactionType type) {
		BigDecimal newBalance = account.getBalance();
		if (TransactionType.CREDIT.equals(type))
			newBalance = newBalance.add(amount);
		else {
			//we can implement some business logic for negative balance
			newBalance = newBalance.subtract(amount);
		}
		account.setBalance(newBalance);
		accountDao.save(account);
		
		String transactionId = commonDao.generateNewEntityCode(Transaction.class, "transactionId", "T", 12);
		
		Transaction transaction = new Transaction(transactionId, account, type, amount);
		accountDao.createTransaction(transaction);
		
		return newBalance;
	}

	@Override
	public long countAccountTransactions(String accountId) {
		return accountDao.countAccountTransactions(accountId);
	}

	@Override
	public List<Transaction> getAccountTransactions(String accountId, int pageNumber, int pageSize) {
		return accountDao.getAccountTransactions(accountId, pageNumber, pageSize);
	}
}
