package com.example.demo.business;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class YczdMiniAuth2Provider implements AuthenticationProvider {
	private final String code = "root";

	//根用户拥有全部的权限
	private final List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),
			new SimpleGrantedAuthority("ROLE_URSER"),
			new SimpleGrantedAuthority("ROLE_SUPER"));

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (isMatch(authentication)) {
			User user = new User(authentication.getName(), "", authorities);
			return new YczdMiniAuth2Token(user, authentication.getCredentials(), authorities);
		}
		return null;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (YczdMiniAuth2Token.class.isAssignableFrom(authentication));
	}

	private boolean isMatch(Authentication authentication) {
		if (authentication.getName().equals(code))
			return true;
		else
			return false;
	}

}
