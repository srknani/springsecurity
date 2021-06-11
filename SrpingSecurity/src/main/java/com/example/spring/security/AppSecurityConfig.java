package com.example.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

//	Custom login and logout 
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/admin", "/index").hasRole("USER").and().formLogin().loginPage("/login")
				.and().logout().invalidateHttpSession(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

//	Remembering Password using spring security
//	@Override    
//	protected void configure(HttpSecurity http) throws Exception {    
//	      
//	      http.authorizeRequests().  
//	      antMatchers("/index", "/user","/").permitAll()  
//	      .antMatchers("/").authenticated()  
//	      .and()  
//	      .formLogin()  
//	      .loginPage("/login")  
//	      .and()  
//	      .rememberMe()  
//	      .key("rem-me-key")  
//	      .rememberMeParameter("remember") // it is name of checkbox at login page  
//	      .rememberMeCookieName("rememberlogin") // it is name of the cookie  
//	      .tokenValiditySeconds(100) // remember for number of seconds  
//	      .and()  
//	      .logout()  
//	      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));    
//	}   

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		auth.inMemoryAuthentication().withUser("user").password(encoder.encode("password")).roles("USER").and()
//				.withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");
//	}
//
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//
//		List<UserDetails> users = new ArrayList<>();
//		users.add(org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder().username("srikanth").password("12345").roles("USER").build());
//		return new InMemoryUserDetailsManager(users);
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
