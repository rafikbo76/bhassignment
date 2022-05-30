package com.cpgm.bh.bhassignment.ws.rest.filters;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.cpgm.bh.bhassignment.jpa.security.User;

public class UserSecurityContext implements SecurityContext {
	
	private Principal principal;
	
	public UserSecurityContext(User user) {
		principal = user;
	}

	@Override
	public Principal getUserPrincipal() {
		return principal; 
	}

	@Override
	public boolean isUserInRole(String role) {
		return ((User) principal).getRole().getRoleName().equalsIgnoreCase(role);
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public String getAuthenticationScheme() {
		return null;
	}

}
