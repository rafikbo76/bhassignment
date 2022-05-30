package com.cpgm.bh.bhassignment.ws.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cpgm.bh.bhassignment.constants.BhAssignmentRolesConstant;
import com.cpgm.bh.bhassignment.dto.CustomerAccountDto;
import com.cpgm.bh.bhassignment.dto.CustomerDto;
import com.cpgm.bh.bhassignment.jpa.customer.Customer;
import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;
import com.cpgm.bh.bhassignment.security.Secured;
import com.cpgm.bh.bhassignment.ws.rest.service.CustomerService;

@Component
@Path("/customers")
//@Secured(roles={BhAssignmentRolesConstant.ADMIN,BhAssignmentRolesConstant.ACCOUNT_MANAGER, BhAssignmentRolesConstant.CUSTOMER})

public class CustomerResource {
	
	@Autowired
	CustomerService customerService;
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, Object> getAllCustomers(@QueryParam("start") int iDisplayStart, @QueryParam("length") int iDisplayLength){
		
		Map<String, Object> ret = new HashMap<String, Object>();
		
		List<CustomerDto> dtoResult=new ArrayList<CustomerDto>();
		
		if (iDisplayLength==0)
			iDisplayLength=10;
		
		List<Customer> customers = customerService.getCustomers(iDisplayStart, iDisplayLength);
		Long count = customerService.countCustomers();
		
		for (Customer customer : customers) {
			dtoResult.add(new CustomerDto(customer));
		}
		
		ret.put("iTotalRecords", count!=null?count:0);
		ret.put("data", dtoResult);
		
		return ret;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{customerId}/accounts")
	public Map<String, Object> getAllCustomerAccounts(@PathParam("customerId") String customerId, @QueryParam("start") int iDisplayStart, @QueryParam("length") int iDisplayLength){
		
		Map<String, Object> ret = new HashMap<String, Object>();
		
		List<CustomerAccountDto> dtoResult=new ArrayList<CustomerAccountDto>();
		
		if (iDisplayLength==0)
			iDisplayLength=10;
		
		List<CustomerAccount> accounts = customerService.getCustomerAccounts(customerId, iDisplayStart, iDisplayLength);
		Long count = customerService.countCustomerAccounts(customerId);
		
		for (CustomerAccount account : accounts) {
			dtoResult.add(new CustomerAccountDto(account));
		}
		
		ret.put("iTotalRecords", count!=null?count:0);
		ret.put("data", dtoResult);
		
		return ret;
	}
	
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customer = customerService.createCustomer(customerDto);
		return customer!=null?new CustomerDto(customer):null;
	}
}
