package com.cpgm.bh.bhassignment.ws.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import com.cpgm.bh.bhassignment.dto.CustomerDto;
import com.cpgm.bh.bhassignment.jpa.customer.Customer;
import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;

public interface CustomerService {
	
	@Transactional
	Customer createCustomer(CustomerDto customerDto);
	
	List<Customer> getCustomers(int iDisplayStart, int iDisplayLength);

	Long countCustomers();

	List<CustomerAccount> getCustomerAccounts(String customerId, int iDisplayStart, int iDisplayLength);

	Long countCustomerAccounts(String customerId);

	Customer getCustomerById(String customerId);
}
