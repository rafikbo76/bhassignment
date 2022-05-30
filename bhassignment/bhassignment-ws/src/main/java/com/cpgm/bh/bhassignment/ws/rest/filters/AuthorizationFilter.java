package com.cpgm.bh.bhassignment.ws.rest.filters;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.cpgm.bh.bhassignment.jpa.security.User;
import com.cpgm.bh.bhassignment.security.Secured;


@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
	
	@Context
    private ResourceInfo resourceInfo;
	
	@Context
	private HttpServletRequest sr;
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		
		String baseUriPath = requestContext.getUriInfo().getBaseUri().toString();
		String endPointPath = requestContext.getUriInfo().getRequestUri().toString();
		endPointPath = endPointPath.substring(baseUriPath.length()-1);
		
		// Get the resource class which matches with the requested URL
        // Extract the roles declared by it
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<String> classRoles = extractRoles(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the roles declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<String> methodRoles = extractRoles(resourceMethod);

        try {
        	UserSecurityContext secContext = (UserSecurityContext) requestContext.getSecurityContext();
        	User user = (User) secContext.getUserPrincipal();
        	System.out.println(user);

        	// Check if the user is allowed to execute the method
            // The method annotations override the class annotations
            if (methodRoles.isEmpty()) {
                checkPermissions(classRoles, secContext);
            } else {
                checkPermissions(methodRoles, secContext);
            }
            
            checkPageRolePermission(requestContext);
        } catch (Exception e) {
        	//Log UnAuthorized Request
        	//e.printStackTrace();
            requestContext.abortWith(
                Response.status(Response.Status.FORBIDDEN).build());
        }
    }

	// Extract the roles from the annotated element
    private List<String> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<String>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<String>();
            } else {
                String [] allowedRoles = secured.roles();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    /*
     * throw Exception if user doesn't have adecquated role
     * All user are allowed if allowedRoles is empty
     */
    private void checkPermissions(List<String> allowedRoles, UserSecurityContext secContext) throws Exception {
//    	if no role all authenticated user are allowed
    	if (allowedRoles.isEmpty())
    		return;
    	
    	// check role 
        for (String role : allowedRoles) {
			if (secContext.isUserInRole(role))
				return;
		}
        
        // if not allowed throw exception
        throw new Exception("Not Allowed");
    }

/*
 *  Check if the source page is compatible with Role
 */
    private void checkPageRolePermission(ContainerRequestContext requestContext) throws Exception {
    	UserSecurityContext secContext = (UserSecurityContext) requestContext.getSecurityContext();
    	User user = (User) secContext.getUserPrincipal();
    	String referer = requestContext.getHeaderString("referer");
    	System.out.println(user);
    	System.out.println(referer);
	}
}
