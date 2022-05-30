package com.cpgm.bh.bhassignment.dto;

import java.util.Date;


import com.cpgm.bh.bhassignment.jpa.security.AccountStatus;
import com.cpgm.bh.bhassignment.jpa.security.User;

public class UserDto {
	
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNumber;
	private String roleCode;
	private String roleName;
	private Date registrationDate;
	private Date expirationDate;
	private AccountStatus status;

	public UserDto() {
		
	}
	
	public UserDto(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.phoneNumber = user.getPhoneNumber();
		if (user.getRole()!=null) {
			this.roleCode=user.getRole().getRoleCode();
			this.roleName = user.getRole().getRoleName();
		}
		this.registrationDate = user.getRegistrationDate();
		this.expirationDate = user.getExpirationDate();
		this.status = user.getStatus();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}
}
