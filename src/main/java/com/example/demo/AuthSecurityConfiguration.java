package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.business.YczdMiniAuth2Filter;
import com.example.demo.business.YczdMiniAuth2Provider;
import com.example.demo.business.YczdMiniAuth2SuccessHandler;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userService;
	@Autowired
	YczdMiniAuth2Provider yczdMiniAuth2Provider;

	@Bean
	YczdMiniAuth2SuccessHandler yczdMiniAuth2SuccessHandler() {
		return new YczdMiniAuth2SuccessHandler();
	}

	@Bean
	YczdMiniAuth2Filter yczdMiniAuth2Filter() throws Exception {
		YczdMiniAuth2Filter yczdMiniAuth2Filter = new YczdMiniAuth2Filter();
		yczdMiniAuth2Filter.setAuthenticationManager(authenticationManager());
		yczdMiniAuth2Filter.setAuthenticationSuccessHandler(yczdMiniAuth2SuccessHandler());
		return yczdMiniAuth2Filter;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
				.and().authenticationProvider(yczdMiniAuth2Provider);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/", "/index").permitAll()
				.antMatchers("/login/code").permitAll()
				.antMatchers("/login/qq").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasRole("USER")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.and()
				.httpBasic()
				.and()
				.logout()
				.and()
				.rememberMe()
				.and()
				.addFilterBefore(yczdMiniAuth2Filter(), UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
}
