package com.cpgm.bh.bhassignment.ws.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;
import com.cpgm.bh.bhassignment.jpa.customer.Transaction;

public class AccountDaoJpa2Impl implements AccountDao {
	
	@PersistenceContext(unitName="bhassignmentPersistence")
	private EntityManager entityManager;

	@Override
	public CustomerAccount getAccountById(String accountId) {
		return entityManager.find(CustomerAccount.class, accountId);
	}
	
	@Override
	public void createAccount(CustomerAccount account) {
		entityManager.persist(account);
	}

	@Override
	public CustomerAccount save(CustomerAccount account) {
		return entityManager.merge(account);
	}

	@Override
	public Long countAccountTransactions(String accountId) {
		CriteriaBuilder crtBuild = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> crtQuery = crtBuild.createQuery(Long.class);
		
		Root<Transaction> root = crtQuery.from(Transaction.class);
		
		Predicate pElAccountId = crtBuild.equal(root.get("account").get("accountId"),accountId); 
        
		crtQuery.select(crtBuild.count(root)); 
		
		crtQuery.where(pElAccountId);
        		
		TypedQuery<Long> q = entityManager.createQuery(crtQuery);
        
        try{
        	Long count = q.getSingleResult();
        	return count;
        }
        catch (NoResultException nex){
        	return null;
        }
	}

	@Override
	public List<Transaction> getAccountTransactions(String accountId, int pageNumber, int pageSize) {
		CriteriaBuilder crtBuild = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transaction> crtQuery = crtBuild.createQuery(Transaction.class);
		
		Root<Transaction> root = crtQuery.from(Transaction.class);
		
		Predicate pElAccountId = crtBuild.equal(root.get("account").get("accountId"),accountId); 
        
		crtQuery.select(root); 
		
		crtQuery.where(pElAccountId);
        		
		TypedQuery<Transaction> q = entityManager.createQuery(crtQuery);
		q.setFirstResult(pageNumber*pageSize);
		q.setMaxResults(pageSize);
        
        try{
        	List<Transaction> transactions = q.getResultList();
        	return transactions;
        }
        catch (NoResultException nex){
        	return null;
        }
	}

	@Override
	public void createTransaction(Transaction transaction) {
		entityManager.persist(transaction);
	}

}
