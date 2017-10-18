package tn.undefined.universalhaven.resources;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import tn.undefined.universalhaven.enumerations.UserRole;
import tn.undefined.universalhaven.buisness.UserServiceLocalMehdi;
import tn.undefined.universalhaven.util.KeyGenerator;
import tn.undefined.universalhaven.util.SimpleKeyGenerator;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class JWTTokenIssuer {
	 	
	
		@EJB
		UserServiceLocalMehdi userService;
		
	    private KeyGenerator keyGenerator = new SimpleKeyGenerator();
	 
	    @POST
	    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	    public Response authenticateUser(@FormParam("login") String login,
	                                     @FormParam("password") String password) {
	        try {
	 
	            // Authenticate the user using the credentials provided
	            UserRole role = userService.authenticate(login, password);
	            
	            // Issue a token for the user
	            String token = issueToken(login,role);
	 
	            // Return the token on the response
	            return Response.ok().header("AUTHORIZED", "Bearer " + token).build();
	 
	        } catch (Exception e) {
	            return Response.status(Response.Status.UNAUTHORIZED).build();
	        }
	    }
	    
	    private void authenticate(String login, String password) throws Exception {
	    	if (login.equals("meedoch")==false) {
	    		throw new Exception();
	    	}
	    }
	    
	    private String issueToken(String login,UserRole role) {
	        Key key = keyGenerator.generateKey();
	        Calendar c =Calendar.getInstance();
	        c.add(Calendar.MINUTE, 15);
	        String jwtToken = Jwts.builder()
	                .setSubject(login+"-"+role)
	                .setIssuer("universal-haven.com")
	                .setIssuedAt(new Date())
	                .setExpiration(c.getTime() )
	                .signWith(SignatureAlgorithm.HS512, key)
	                .compact();
	        return jwtToken;
	    }
}
