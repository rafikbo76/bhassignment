package com.cpgm.bh.bhassignment.dto;

import java.util.Date;

import com.cpgm.bh.bhassignment.jpa.security.Role;


public class RoleDto {
	
	private String roleCode;
	private String roleName;
	private Date registrationDate;
	
	public RoleDto() {
		
	}
	
	public RoleDto(Role role) {
		this.roleCode = role.getRoleCode();
		this.roleName = role.getRoleName();
		this.registrationDate = role.getRegistrationDate();
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
}
