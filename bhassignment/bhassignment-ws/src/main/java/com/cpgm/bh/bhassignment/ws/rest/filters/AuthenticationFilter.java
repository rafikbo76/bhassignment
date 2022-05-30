package com.cpgm.bh.bhassignment.ws.rest.filters;

import java.io.IOException;
import java.util.Calendar;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpgm.bh.bhassignment.jpa.security.AccountStatus;
import com.cpgm.bh.bhassignment.jpa.security.Token;
import com.cpgm.bh.bhassignment.security.Secured;
import com.cpgm.bh.bhassignment.ws.rest.service.SecurityService;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	
	@Autowired
	SecurityService secService;
	
	@Context
	private HttpServletRequest sr;
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		
		String token = null;
		String baseUriPath = requestContext.getUriInfo().getBaseUri().toString();
		String endPointPath = requestContext.getUriInfo().getRequestUri().toString();
		endPointPath = endPointPath.substring(baseUriPath.length()-1);
		
        try {
        	// Extract the token from the HTTP Authorization header
            token = extractTokenFromAuthorizationHeader(requestContext);

            // Validate the token
            Token dbToken = validateToken(token);
            
            //set User in SecurityContext
            requestContext.setSecurityContext(new UserSecurityContext(dbToken.getUser()));
            
            
    		if (dbToken.getUser().getStatus().equals(AccountStatus.DISABLED)){//if user was disabled then logout
    			secService.logout(dbToken.getTokenValue(), sr.getRemoteAddr());
    			throw new Exception("Invalid Token");
    		}

        } catch (Exception e) {
        	e.printStackTrace();
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }
	}

	private Token validateToken(String token) throws Exception {
		Token dbToken = secService.getTokenByValue(token);
		if ((dbToken==null)||(dbToken.getExpireDate().before(Calendar.getInstance().getTime())))
			throw new Exception("Invalid Token");
		
		return dbToken;
	}
	
	public static String extractTokenFromAuthorizationHeader(ContainerRequestContext requestContext){
		// Get the HTTP Authorization header from the request
        String authorizationHeader = 
            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly 
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();
        
        return token;
	}
	
	public static String extractPasswordFromAuthenticationHeader(ContainerRequestContext requestContext){
		// Get the HTTP Authorization header from the request
        String wwwAauthenticationHeader = 
            requestContext.getHeaderString(HttpHeaders.WWW_AUTHENTICATE);

        // Check if the HTTP Authorization header is present and formatted correctly 
        if (wwwAauthenticationHeader == null || !wwwAauthenticationHeader.startsWith("Password ")) {
            return null;
        }

        // Extract the token from the HTTP Authorization header
        String password = wwwAauthenticationHeader.substring("Password".length()).trim();
        
        return password;
	}
}
