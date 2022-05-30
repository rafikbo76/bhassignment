package com.cpgm.bh.bhassignment.ws.rest.service;

import com.cpgm.bh.bhassignment.dto.RoleDto;
import com.cpgm.bh.bhassignment.dto.UserDto;
import com.cpgm.bh.bhassignment.jpa.security.Token;
import com.cpgm.bh.bhassignment.jpa.security.User;

public interface SecurityService {
	
	void createRole(RoleDto role);
	
	void createUser(UserDto user);

	User login(String username, String password);
	
	void logout(String tokenValue, String remoteAddr);

	Token getTokenByValue(String token);

}
