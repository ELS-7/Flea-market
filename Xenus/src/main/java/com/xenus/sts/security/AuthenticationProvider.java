package com.xenus.sts.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

import com.xenus.sts.vo.LoginUserSecurity;


public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    //Instance fields
	//private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();

    private SaltSource saltSource;
    private UserDetailsService userDetailsService;
    private boolean includeDetailsObject = true;
    
    //Methods
//    protected void additionalAuthenticationChecks(UserDetails userDetails,
//            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        Object salt = null;
//
//        if (this.saltSource != null) {
//            salt = this.saltSource.getSalt(userDetails);
//        }
//
//        if (authentication.getCredentials() == null) {
//            logger.debug("Authentication failed: no credentials provided");
//
//            throw new BadCredentialsException(messages.getMessage(
//                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"),
//                    includeDetailsObject ? userDetails : null);
//        }
//
//        String presentedPassword = authentication.getCredentials().toString();
//
//        if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
//            logger.debug("Authentication failed: password does not match stored value");
//
//            throw new BadCredentialsException(messages.getMessage(
//                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"),
//                    includeDetailsObject ? userDetails : null);
//        }
//    }

    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }

    //Login Start
    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        LoginUserSecurity 	  userDetails = null;
        xenusUserLoginService aphLoginService = (xenusUserLoginService)this.getUserDetailsService();
        String presentedPassword = authentication.getCredentials().toString();
        
        //accessKey login
        //try {
        //	IntegrationAuthenticationToken inAuthentication = (IntegrationAuthenticationToken) authentication;
        //	userDetails =(LoginUserSecurity)aphLoginService.loadUserByUsername(username, presentedPassword, inAuthentication.getAccessKey());
        //} catch (Exception ex) {}
        
        //userId Login
        try {
        	if (userDetails == null) { 
        		try {
					userDetails =(LoginUserSecurity)aphLoginService.loadUserByUsername(username, presentedPassword, "");
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        } catch (DataAccessException repositoryProblem) {
        	throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (userDetails == null) {
            throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }
        
        return userDetails;
    }

    /**
     * Sets the PasswordEncoder instance to be used to encode and validate passwords.
     * If not set, {@link PlaintextPasswordEncoder} will be used by default.
     *
     * @param passwordEncoder The passwordEncoder to use
     */
//    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    protected PasswordEncoder getPasswordEncoder() {
//        return passwordEncoder;
//    }

    /**
     * The source of salts to use when decoding passwords. <code>null</code>
     * is a valid value, meaning the <code>DaoAuthenticationProvider</code>
     * will present <code>null</code> to the relevant <code>PasswordEncoder</code>.
     *
     * @param saltSource to use when attempting to decode passwords via the <code>PasswordEncoder</code>
     */
    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    protected SaltSource getSaltSource() {
        return saltSource;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    protected boolean isIncludeDetailsObject() {
        return includeDetailsObject;
    }

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
			// TODO Auto-generated method stub
	}

    /**
     * Determines whether the UserDetails will be included in the <tt>extraInformation</tt> field of a
     * thrown BadCredentialsException. Defaults to true, but can be set to false if the exception will be
     * used with a remoting protocol, for example.
     *
     * @deprecated use {@link org.springframework.security.authentication.ProviderManager#setClearExtraInformation(boolean)}
     */
//    public void setIncludeDetailsObject(boolean includeDetailsObject) {
//        this.includeDetailsObject = includeDetailsObject;
//    }
}
