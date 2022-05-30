package com.cpgm.bh.bhassignment.ws.rest.dao;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CommonDaoJpa2Impl implements CommonDao {
	
	@PersistenceContext(unitName="bhassignmentPersistence")
	private EntityManager entityManager;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String generateNewEntityCode(Class clazz, String columnName, String pref, int length) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		Root from = criteriaQuery.from(clazz);
		Predicate pElPrefix = criteriaBuilder.like(from.get(columnName),pref+"%"); 
		 
		Expression maxExpression = criteriaBuilder.max(from.get(columnName));
		CriteriaQuery<String> select = criteriaQuery.select(maxExpression);
		select.where(pElPrefix);
		
		 
		TypedQuery<String> typedQuery = entityManager.createQuery(select);
		
		
		String maxExpected = typedQuery.getSingleResult();
		
		String zeroSequence = getStringWithLengthAndFilledWithCharacter(length, '0');
		
		String code = pref+  zeroSequence.substring(pref.length()+1)+"1";
		if (maxExpected!=null){
			Integer j=Integer.parseInt(maxExpected.substring(pref.length()));
			j++;
			code = pref+zeroSequence.substring(pref.length()+String.valueOf(j).length())+j;
		}
		return code;
	}
	
	private String getStringWithLengthAndFilledWithCharacter(int length, char charToFill) {
		  if (length > 0) {
		    char[] array = new char[length];
		    Arrays.fill(array, charToFill);
		    return new String(array);
		  }
		  return "";
	}
}
