package com.cpgm.bh.bhassignment.ws.rest.dao;

import java.util.List;

import com.cpgm.bh.bhassignment.jpa.customer.Customer;
import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;

public interface CustomerDao {

	void createCustomer(Customer customer);

	Customer getCustomerById(String customerId);

	List<Customer> getCustomers(int iDisplayStart, int iDisplayLength);

	Long countCustomers();

	List<CustomerAccount> getCustomerAccounts(String customerId, int iDisplayStart, int iDisplayLength);

	Long countCustomerAccounts(String customerId);

}
