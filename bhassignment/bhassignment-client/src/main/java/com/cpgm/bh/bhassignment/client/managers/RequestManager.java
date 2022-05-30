package com.cpgm.bh.bhassignment.client.managers;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.cpgm.bh.bhassignment.dto.CustomerAccountDto;
import com.cpgm.bh.bhassignment.dto.CustomerDto;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class RequestManager {
	private final String WSURL = "http://localhost:8082/bhassignment-ws/rest";
	private final String CustomerPath = "/customers";
	private final String AccountPath = "/accounts";
	
	public CustomerDto createCustomer(CustomerDto dto){
		ClientConfig clientConfig = new DefaultClientConfig();
//        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		WebResource webResource = client.resource(WSURL+CustomerPath);
		
		CustomerDto response = (CustomerDto) webResource.type(MediaType.APPLICATION_XML).post(CustomerDto.class, dto);

		return response;
	}
	
	public CustomerAccountDto addAccount(String customerId, BigDecimal initialBalance){
		ClientConfig clientConfig = new DefaultClientConfig();
//        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		WebResource webResource = client.resource(WSURL+AccountPath);
		
		MultivaluedMap formData = new com.sun.jersey.core.util.MultivaluedMapImpl();
		formData.add("customerId", customerId);
		formData.add("initialBalance", initialBalance.toString());
		CustomerAccountDto response = (CustomerAccountDto)webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(CustomerAccountDto.class, formData);

		return response;
	}
	
	
//	public BusinessUnit getBusinessUnitById(String bunitId){
//		ClientConfig clientConfig = new DefaultClientConfig();
//		Client client = Client.create(clientConfig);
//		
//		WebResource webResource = client.resource(PetrolPayClientConstants.WSURL+BusinessUnitClassPath);
//		
//		BusinessUnit response =  webResource.queryParam("bunitId", bunitId).accept(MediaType.APPLICATION_XML).get(BusinessUnit.class);
//
//		return response;
//	}
//	
//	public List<BusinessUnit> getAllBusinessUnits(String token){
//		System.out.println("Authentication Header ------------------------------ "+token);
//		
//		ClientConfig clientConfig = new DefaultClientConfig();
//		Client client = Client.create(clientConfig);
//		
//		WebResource webResource = client.resource(PetrolPayClientConstants.WSURL+BusinessUnitClassPath+"/all");
//		List<BusinessUnit> response = null;
//		
//		if (token!=null)
//			response =  webResource.accept(MediaType.APPLICATION_XML).header("Authorization", "Bearer "+token).get(new GenericType<List<BusinessUnit>>(){});
//		else
//			response =  webResource.accept(MediaType.APPLICATION_XML).get(new GenericType<List<BusinessUnit>>(){});
//
//		return response;
//	}
}
