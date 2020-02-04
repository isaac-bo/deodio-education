package com.deodio.core.security.helper;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.deodio.core.security.model.UserModel;


/**
 * Spring Seacurity Help Class
 * 
 * @author pactera
 *
 */
public class SpringSecurityHelper {
    private SpringSecurityHelper(){
        
    }

    /**
     * Get the current user from spring security context.
     * @return T UserModel
     */
    @SuppressWarnings("unchecked")
    public static <T extends UserModel> T getCurrentUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserModel)) {
            return null;
        }
        return (T) principal;
    }
    
    /**
     * Get User Id
     * @return Id   User PkId
     */
    public static Long getCurrentUserId() {
        UserModel um = getCurrentUser();
        if(null != um){
            return um.getId();
        }
        return null;
        
    }

    /**
     *  Get the current user login name from spring security context.
     */
    public static String getCurrentUserName() {
        Authentication authentication = getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return "";
        }
        return authentication.getName();
    }

    /**
     *  Get the current user real name from spring security context.
     */
    public static String getCurrentName() {
        return getCurrentUser().getName();
    }

    /**
     *  Get the current user  IP address
     */
    public static String getCurrentUserIp() {
        Authentication authentication = getAuthentication();

        if (authentication == null) {
            return "";
        }

        Object details = authentication.getDetails();
        if (!(details instanceof WebAuthenticationDetails)) {
            return "";
        }

        WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
        return webDetails.getRemoteAddress();
    }

    /**
     * Determine whether the user has some role ,if has return true
     * @param roles  System's roles array.
     * @return Whether it has the roles
     */
    public static boolean hasAnyRole(String... roles) {
        Authentication authentication = getAuthentication();

        if (authentication == null) {
            return false;
        }

        Collection<GrantedAuthority> grantedAuthorityList = (Collection<GrantedAuthority>) authentication.getAuthorities();
        for (String role : roles) {
            for (GrantedAuthority authority : grantedAuthorityList) {
                if (role.equals(authority.getAuthority())) {
                    return true;
                }
            }
        }

        return false;
    }
    /**
     * Save user info to spring security context.
     * @param userDetails user info model class 
     * @param request  HttpServletRequest
     */
    public static void saveUserDetailsToContext(UserDetails userDetails,
            HttpServletRequest request) {
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
                userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        if (request != null) {
            authentication.setDetails(new WebAuthenticationDetails(request));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * get Authentication from spring security context.
     */
    private static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context == null) {
            return null;
        }

        return context.getAuthentication();
    }

}
