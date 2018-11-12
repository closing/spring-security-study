package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Authority;
import com.example.demo.model.Credentials;
import com.example.demo.service.AuthUserService;

@Service
public class AuthUserDetailsServiceImpl implements AuthUserService {

	private static Map<String, Credentials> users = new HashMap<>();
	static {
		Credentials user = new Credentials();
		user.setId(1);
		user.setEnabled(true);
		user.setName("admin");
		user.setPassword("{noop}password");
		user.setVersion(0);
		List<Authority> authorities = new ArrayList<>();
		Authority authority = new Authority();
		authority.setAuthorityId(1);
		authority.setAuthority("ROLE_ADMIN");
		authorities.add(authority);
		user.setAuthorities(authorities);
		users.put(user.getName(), user);

		user = new Credentials();
		user.setId(3);
		user.setEnabled(true);
		user.setName("user");
		user.setPassword("{noop}password");
		user.setVersion(0);
		authorities = new ArrayList<>();
		authority = new Authority();
		authority.setAuthorityId(2);
		authority.setAuthority("ROLE_PRODUCT");
		authorities.add(authority);
		authority = new Authority();
		authority.setAuthorityId(4);
		authority.setAuthority("ROLE_SHOP");
		authorities.add(authority);
		authority = new Authority();
		authority.setAuthorityId(5);
		authority.setAuthority("ROLE_USER");
		authorities.add(authority);
		user.setAuthorities(authorities);
		users.put(user.getName(), user);

		user = new Credentials();
		user.setId(4);
		user.setEnabled(true);
		user.setName("client");
		user.setPassword("{noop}password");
		user.setVersion(0);
		authorities = new ArrayList<>();
		authority = new Authority();
		authority.setAuthorityId(5);
		authority.setAuthority("ROLE_CLIENT");
		authorities.add(authority);
		user.setAuthorities(authorities);
		users.put(user.getName(), user);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Credentials credentials = users.get(username);

		if (credentials == null) {

			throw new UsernameNotFoundException("User" + username + "can not be found");
		}
		User user = new User(credentials.getName(), credentials.getPassword(),
				credentials.isEnabled().booleanValue(),
				true, true, true, credentials.getAuthorities());

		return user;
	}

}
