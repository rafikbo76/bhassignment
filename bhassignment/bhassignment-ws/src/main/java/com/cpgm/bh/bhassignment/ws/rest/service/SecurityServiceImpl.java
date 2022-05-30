package com.cpgm.bh.bhassignment.ws.rest.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpgm.bh.bhassignment.dto.RoleDto;
import com.cpgm.bh.bhassignment.dto.UserDto;
import com.cpgm.bh.bhassignment.jpa.security.Token;
import com.cpgm.bh.bhassignment.jpa.security.User;
import com.cpgm.bh.bhassignment.ws.rest.dao.SecurityDao;

public class SecurityServiceImpl implements SecurityService {
	
	@Autowired
	SecurityDao secDao;

	@Override
	public void createRole(RoleDto role) {
		secDao.createRole(role);
	}

	@Override
	public void createUser(UserDto user) {
		secDao.createuser(user);
	}

	@Override
	public User login(String username, String password) {
		return secDao.login(username, password);
	}

	@Override
	public void logout(String tokenValue, String remoteAddr) {
		secDao.logout(tokenValue, remoteAddr);
	}

	@Override
	public Token getTokenByValue(String token) {
		return secDao.getTokenByValue(token);
	}
}
