//package com.login.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//
//import com.login.filter.JWTAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = false)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	/**
//	 * 需要放行的URL
//	 */
//	public static final String[] AUTH_WHITELIST = { "/test/tuser", "/login2"
//			// other public endpoints of your API may be appended to this array
//	};
//
//	@Autowired
//	private DataSource dataSource;
//
//	@Bean
//	public JdbcTokenRepositoryImpl tokenRepositoryImpl() {
//		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//		tokenRepository.setDataSource(dataSource);
//
//		return tokenRepository;
//	}
//
//	@Bean
//	UserDetailsService customUserService() {
//		return null;
//		// return new CustomUserServiceImpl();
//	}
//
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
//	}
//
//	public void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated().and()
//				.addFilter(new JWTAuthenticationFilter(authenticationManager()));
//	}
//
//}
