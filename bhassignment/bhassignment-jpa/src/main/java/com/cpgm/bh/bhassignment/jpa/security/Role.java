package com.cpgm.bh.bhassignment.jpa.security;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@Column(name="ROLCOD", length=30)
	private String roleCode;
	
	@Column(name="ROLNAM", length=50)
	private String roleName;
	
	@Column(name="REGDAT")
	private Date registrationDate;

	public Role() {
		
	}

	public Role(String roleCode, String roleName) {
		super();
		this.roleCode = roleCode;
		this.roleName = roleName;
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
	
	@PrePersist
	public void setDefaultValues() {
		setRegistrationDate(Calendar.getInstance().getTime());
	}
}
