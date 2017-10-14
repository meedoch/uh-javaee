package jwt;

import io.jsonwebtoken.Jwts;


import javax.annotation.Priority;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

import tn.undefined.universalhaven.enumerations.UserRole;
import tn.undefined.universalhaven.util.KeyGenerator;
import tn.undefined.universalhaven.util.SimpleKeyGenerator;

import java.util.logging.Logger;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

  
	@Context
    private ResourceInfo resourceInfo;
    
    private KeyGenerator keyGenerator= new SimpleKeyGenerator();
    
  
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        System.out.println("#### authorizationHeader : " + authorizationHeader);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        	System.err.println("#### invalid authorizationHeader : " + authorizationHeader);
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();
        
        try {

            // Validate the token
        	UserRole role = (resourceInfo.getResourceMethod().getAnnotation(JWTTokenNeeded.class).role());
            
        	Key key = keyGenerator.generateKey();
            String subj =Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
            System.out.println("SUBJECT : "+subj);
            System.out.println("ROLE ANNOTATION : "+role);
            if (subj.contains(role.toString())==false) {
            	throw new Exception();
            }
            System.out.println("#### valid token : " + token);
            
            
        } catch (Exception e) {
        	System.err.println("#### invalid token : " + token);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
            		.entity("Invalid JWT Token")
            		.build());
        }
    }
}
