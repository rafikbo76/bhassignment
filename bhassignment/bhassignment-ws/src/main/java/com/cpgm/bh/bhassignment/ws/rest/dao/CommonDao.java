package com.cpgm.bh.bhassignment.ws.rest.dao;

public interface CommonDao {
	
	@SuppressWarnings("rawtypes")
	String generateNewEntityCode(Class clazz, String columnName, String prefix, int length);

}
