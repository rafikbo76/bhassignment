package com.cpgm.bh.bhassignment.ws.rest.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cpgm.bh.bhassignment.dto.RoleDto;
import com.cpgm.bh.bhassignment.dto.UserDto;
import com.cpgm.bh.bhassignment.jpa.security.AccountStatus;
import com.cpgm.bh.bhassignment.jpa.security.Role;
import com.cpgm.bh.bhassignment.jpa.security.Token;
import com.cpgm.bh.bhassignment.jpa.security.User;
import com.cpgm.bh.bhassignment.ws.rest.utils.CryptoUtil;

public class SecurityDaoJpa2Impl implements SecurityDao {
	
	@PersistenceContext(unitName="bhassignmentPersistence")
	private EntityManager entityManager;


	@Override
	public void createRole(RoleDto role) {
		Role roleD = new Role(role.getRoleCode(), role.getRoleName());
		entityManager.persist(roleD);
	}

	@Override
	public void createuser(UserDto user) {
		Role role = getRoleByCode(user.getRoleCode());
		if (role==null)
			throw new RuntimeException("unknown Role");
		
		String encodedPassword = CryptoUtil.encodePassword(user.getPassword(), null);
		
		User userDb = new User(user.getUsername(), encodedPassword, user.getFirstname(), user.getLastname(), user.getEmail(), user.getPhoneNumber(), role, user.getStatus());
		entityManager.persist(userDb);
	}

	private Role getRoleByCode(String roleCode) {
		CriteriaBuilder crtBuild = entityManager.getCriteriaBuilder();
		CriteriaQuery<Role> crtQuery = crtBuild.createQuery(Role.class);
		
		Root<Role> root = crtQuery.from(Role.class);
		Predicate pERoleCode = crtBuild.equal(root.get("roleCode"),roleCode); 
        		
		crtQuery.select(root); 
        crtQuery.where(pERoleCode);
        
        TypedQuery<Role> q = entityManager.createQuery(crtQuery);
        
        try{
        	Role role = q.getSingleResult();
        	return role;
        }
        catch (NoResultException nex){
        	return null;
        }
        catch (javax.persistence.NonUniqueResultException nuEx){
        	return null;
        }
	}

	@Override
	public User login(String username, String password) {
		CriteriaBuilder crtBuild = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> crtQuery = crtBuild.createQuery(User.class);
		
		Root<User> root = crtQuery.from(User.class);
		Predicate pElusername = crtBuild.equal(root.get("username"), username); 
		//Predicate pElActif = crtBuild.equal(root.get("status"), AccountStatus.ENABLED); 
		
		crtQuery.select(root); 
		crtQuery.where(pElusername);
        
        TypedQuery<User> q = entityManager.createQuery(crtQuery);
        
        try{
    		//get the User by username and than check db encoded password against the given password
        	User user = q.getSingleResult();
        	
        	if ((user.getStatus().equals(AccountStatus.ENABLED))&&(CryptoUtil.checkEncodedPassword(password, user.getPassword()))){
	        	return user;
        	}
        	return null;
        }
        catch (Exception nex){
        	nex.printStackTrace();
        	return null;
        }
	}

	@Override
	public void logout(String tokenValue, String remoteAddr) {
		Token dbToken = getTokenByValue(tokenValue);
		
		entityManager.remove(dbToken);
	}

	@Override
	public Token getTokenByValue(String tokenVal) {
		CriteriaBuilder crtBuild = entityManager.getCriteriaBuilder();
		CriteriaQuery<Token> crtQuery = crtBuild.createQuery(Token.class);
		
		String encodedToken = CryptoUtil.encodeToken(tokenVal);
		
		Root<Token> root = crtQuery.from(Token.class);
		Predicate pETokenVal = crtBuild.equal(root.get("tokenValue"), encodedToken); 
        		
		crtQuery.select(root); 
        crtQuery.where(pETokenVal);
        
        TypedQuery<Token> q = entityManager.createQuery(crtQuery);
        
        try{
        	Token token = q.getSingleResult();
        	return token;
        }
        catch (NoResultException nex){
        	return null;
        }
        catch (javax.persistence.NonUniqueResultException nuEx){
        	return null;
        }
	}
}
