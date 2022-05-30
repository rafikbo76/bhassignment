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

import com.cpgm.bh.bhassignment.jpa.customer.Customer;
import com.cpgm.bh.bhassignment.jpa.customer.CustomerAccount;

public class CustomerDaoJpa2Impl implements CustomerDao {
	
	@PersistenceContext(unitName="bhassignmentPersistence")
	private EntityManager entityManager;

	@Override
	public void createCustomer(Customer customer) {
		entityManager.persist(customer);
	}

	@Override
	public Customer getCustomerById(String customerId) {
		return entityManager.find(Customer.class, customerId);
	}

	@Override
	public List<Customer> getCustomers(int iDisplayStart, int iDisplayLength) {
		CriteriaBuilder crtBuild = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> crtQuery = crtBuild.createQuery(Customer.class);
		
		Root<Customer> root = crtQuery.from(Customer.class);
        
		crtQuery.select(root); 
        		
		TypedQuery<Customer> q = entityManager.createQuery(crtQuery);
		q.setFirstResult(iDisplayStart*iDisplayLength);
		q.setMaxResults(iDisplayLength);
        
        try{
        	List<Customer> customers = q.getResultList();
        	return customers;
        }
        catch (NoResultException nex){
        	return null;
        }
	}

	@Override
	public Long countCustomers() {
		CriteriaBuilder crtBuild = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> crtQuery = crtBuild.createQuery(Long.class);
		
		Root<Customer> root = crtQuery.from(Customer.class);
		
		crtQuery.select(crtBuild.count(root)); 
		
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
	public List<CustomerAccount> getCustomerAccounts(String customerId, int iDisplayStart, int iDisplayLength) {
		CriteriaBuilder crtBuild = entityManager.getCriteriaBuilder();
		CriteriaQuery<CustomerAccount> crtQuery = crtBuild.createQuery(CustomerAccount.class);
		
		Root<CustomerAccount> root = crtQuery.from(CustomerAccount.class);
		
		Predicate pElCustomerId = crtBuild.equal(root.get("customer").get("customerId"),customerId); 
        
		crtQuery.select(root); 
		
		crtQuery.where(pElCustomerId);
        		
		TypedQuery<CustomerAccount> q = entityManager.createQuery(crtQuery);
		q.setFirstResult(iDisplayStart*iDisplayLength);
		q.setMaxResults(iDisplayLength);
        
        try{
        	List<CustomerAccount> accounts = q.getResultList();
        	return accounts;
        }
        catch (NoResultException nex){
        	return null;
        }
	}

	@Override
	public Long countCustomerAccounts(String customerId) {
		CriteriaBuilder crtBuild = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> crtQuery = crtBuild.createQuery(Long.class);
		
		Root<CustomerAccount> root = crtQuery.from(CustomerAccount.class);
		
		Predicate pElCustomerId = crtBuild.equal(root.get("customer").get("customerId"),customerId); 
        
		crtQuery.select(crtBuild.count(root)); 
		
		crtQuery.where(pElCustomerId);
        		
		TypedQuery<Long> q = entityManager.createQuery(crtQuery);
        
        try{
        	Long count = q.getSingleResult();
        	return count;
        }
        catch (NoResultException nex){
        	return null;
        }
	}
}
