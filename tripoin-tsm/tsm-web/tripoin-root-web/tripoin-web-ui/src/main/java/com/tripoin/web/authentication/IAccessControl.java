package com.tripoin.web.authentication;

/**
 * Simple interface for authentication and authorization checks.
 * 
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IAccessControl {
    public boolean signIn(String username, String password);

    public boolean isUserSignedIn();
    
    public String getUsername();
    
    public String getRole();
    
}
