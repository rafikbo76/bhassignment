package com.cpgm.bh.bhassignment.ws.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpgm.bh.bhassignment.dto.CustomerDto;
import com.cpgm.bh.bhassignment.jpa.customer.Customer;
import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;
import com.cpgm.bh.bhassignment.ws.rest.dao.CommonDao;
import com.cpgm.bh.bhassignment.ws.rest.dao.CustomerDao;

public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDao custDao;
	
	@Autowired
	CommonDao commonDao;
	
	@Override
	public Customer createCustomer(CustomerDto customerDto) {
		Customer customer = new Customer(customerDto.getCustomerLastName(), customerDto.getCustomerFirstName(), customerDto.getAddress(), customerDto.getCustomerEmail(), customerDto.getCustomerPhone());
		String customerId = commonDao.generateNewEntityCode(Customer.class, "customerId", "C", 12);
		customer.setCustomerId(customerId);
		custDao.createCustomer(customer);
		return customer;
	}
	
	@Override
	public Customer getCustomerById(String customerId) {
		return custDao.getCustomerById(customerId);
	}

	@Override
	public List<Customer> getCustomers(int iDisplayStart, int iDisplayLength) {
		return custDao.getCustomers(iDisplayStart, iDisplayLength);
	}

	@Override
	public Long countCustomers() {
		return custDao.countCustomers();
	}

	@Override
	public List<CustomerAccount> getCustomerAccounts(String customerId, int iDisplayStart, int iDisplayLength) {
		return custDao.getCustomerAccounts(customerId, iDisplayStart, iDisplayLength);
	}

	@Override
	public Long countCustomerAccounts(String customerId) {
		return custDao.countCustomerAccounts(customerId);
	}
}
