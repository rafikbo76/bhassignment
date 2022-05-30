package com.cpgm.bh.bhassignment.ws.rest.dao;

import com.cpgm.bh.bhassignment.dto.RoleDto;
import com.cpgm.bh.bhassignment.dto.UserDto;
import com.cpgm.bh.bhassignment.jpa.security.Token;
import com.cpgm.bh.bhassignment.jpa.security.User;

public interface SecurityDao {

	User login(String username, String password);
	
	void logout(String tokenValue, String remoteAddr);

	Token getTokenByValue(String token);

	void createRole(RoleDto role);

	void createuser(UserDto user);

}
