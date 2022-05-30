package com.cpgm.bh.bhassignment.client;

import java.math.BigDecimal;

import com.cpgm.bh.bhassignment.client.managers.RequestManager;
import com.cpgm.bh.bhassignment.dto.CustomerDto;

public class InsertSomeData {

	public static void main(String[] args) {
		RequestManager man = new RequestManager();
		CustomerDto dto = new CustomerDto(null, "Mohamed", "KAMEL", "Algies", "test@gmail.com", "+213666666666");
		man.createCustomer(dto);
		
		dto = new CustomerDto(null, "Samir", "OMAR", "Paris", "test2@gmail.com", "+213777777777");
		man.createCustomer(dto);
		
		dto = new CustomerDto(null, "Sadio", "MANE", "DAKAR", "test@gmail.com", "+213555555555");
		man.createCustomer(dto);
		
//		man.addAccount("C00000000001", BigDecimal.ZERO);
//		man.addAccount("C00000000001", BigDecimal.ONE);
//		man.addAccount("C00000000001", BigDecimal.TEN);
	}

}
