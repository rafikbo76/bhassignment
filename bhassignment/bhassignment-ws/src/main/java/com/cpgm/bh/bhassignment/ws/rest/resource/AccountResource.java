package com.cpgm.bh.bhassignment.ws.rest.resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cpgm.bh.bhassignment.dto.CustomerAccountDto;
import com.cpgm.bh.bhassignment.dto.TransactionDto;
import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;
import com.cpgm.bh.bhassignment.jpa.customer.Transaction;
import com.cpgm.bh.bhassignment.jpa.customer.TransactionType;
import com.cpgm.bh.bhassignment.ws.rest.service.AccountService;

import javassist.NotFoundException;

@Component
@Path("/accounts")
public class AccountResource {
	
	@Autowired
	AccountService accountService;
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addCustomerAccount(@FormParam("customerId") String customerId, @FormParam("initialBalance") BigDecimal initialBalance) {
		CustomerAccount account;
		try {
			System.out.println("Customer ID " +customerId+", initialBalance "+initialBalance);
			account = accountService.createAccount(customerId, initialBalance);
			return Response.ok().entity(new CustomerAccountDto(account)).build();
		} catch (NotFoundException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/credit")
	public Response creditAccount(@FormParam("accountId") String accountId, @FormParam("type") TransactionType type, @FormParam("amount") BigDecimal amount) {
		BigDecimal newAmount;
		try {
			newAmount = accountService.creditAccount(accountId, amount);
			return Response.ok().entity(newAmount).build();
		} catch (NotFoundException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/debit")
	public Response debitAccount(@FormParam("accountId") String accountId, @FormParam("type") TransactionType type, @FormParam("amount") BigDecimal amount) {
		BigDecimal newAmount;
		try {
			newAmount = accountService.debitAccount(accountId, amount);
			return Response.ok().entity(newAmount).build();
		} catch (NotFoundException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{accountId}/transactions")
	public Map<String, Object> getAllCustomers(@PathParam("accountId")String accountId, @QueryParam("start") int iDisplayStart, @QueryParam("length") int iDisplayLength){
		
		Map<String, Object> ret = new HashMap<String, Object>();
		
		List<TransactionDto> dtoResult=new ArrayList<TransactionDto>();
		if (iDisplayLength==0)
			iDisplayLength = 10;
		
		List<Transaction> transactions = accountService.getAccountTransactions(accountId, iDisplayStart, iDisplayLength);
		Long count = accountService.countAccountTransactions(accountId);
		
		for (Transaction transaction : transactions) {
			dtoResult.add(new TransactionDto(transaction));
		}
		
		ret.put("iTotalRecords", count!=null?count:0);
		ret.put("data", dtoResult);
		
		return ret;
	}

}
