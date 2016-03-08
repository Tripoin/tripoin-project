package com.tripoin.core.security;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.UserData;
import com.tripoin.core.pojo.Role;
import com.tripoin.core.pojo.User;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class SecurityUser extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1448641856987940482L;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;	

    /**
     *
     */
    public SecurityUser() {}
	
	public SecurityUser(User user) {
		if(user != null){
			this.setUsername(user.getUsername());
			this.setPassword(user.getPassword());
			this.setAuth(user.getAuth());
			if(user.getEnabled() == 1)
				this.enabled = true;
			else
				this.enabled = false;
			if(user.getExpiredDate() != null){
				if(user.getExpiredDate().compareTo(new Date()) >= 0)
					this.accountNonExpired = true;
				else
					this.accountNonExpired = false;				
			}else
				this.accountNonExpired = true;
			if(user.getNonLocked() == null || user.getNonLocked() == 1)
				this.accountNonLocked = true;
			else
				this.accountNonLocked = false;			
			this.credentialsNonExpired = true;
			this.setStatus(user.getStatus());
			this.setRemarks(user.getRemarks());
			this.setRole(user.getRole());
		} 
	}
	
    
    public SecurityUser(UserData userData) {
		if(userData != null){
			this.setUsername(userData.getUsername());
			this.setAuth(userData.getAuth());
			if(userData.getEnabled() == 1)
				this.enabled = true;
			else
				this.enabled = false;
			if(userData.getExpiredDate() != null){
				Date expiredDateUserData = new Date();
				try {
					expiredDateUserData = ParameterConstant.FORMAT_DEFAULT.parse(userData.getExpiredDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(expiredDateUserData.compareTo(new Date()) >= 0)
					this.accountNonExpired = true;
				else
					this.accountNonExpired = false;				
			}else
				this.accountNonExpired = true;
			if(userData.getNonLocked() == 1 || userData.getNonLocked() == null)
				this.accountNonLocked = true;
			else
				this.accountNonLocked = false;			
			this.credentialsNonExpired = true;
			this.setStatus(userData.getStatus());
			this.setRemarks(userData.getRemarks());
			Role role = new Role();
			role.setCode(userData.getRoleData().getCode());
			role.setStatus(userData.getRoleData().getStatus());
			role.setRemarks(userData.getRoleData().getRemarks());
			this.setRole(role);
		} 
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		Role userRole = this.getRole();		
		if(userRole != null) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.getCode());
			authorities.add(authority);
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

}
