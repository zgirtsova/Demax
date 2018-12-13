package dev.demax.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import dev.demax.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final UserService userService;
	
	@Autowired
	public SecurityConfiguration(UserService userService) {
		this.userService = userService;
	}	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().disable()
			.csrf().disable()
				.authorizeRequests()
				.antMatchers("/login").anonymous()
				.antMatchers("/fonts/**", "/css/**", "/icon/**", "/img/**", "/js/**").permitAll()
				.antMatchers("/").authenticated()
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/")
				//.failureHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.I_AM_A_TEAPOT))
			.and()
				.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.clearAuthentication(true)
			.and()
				.rememberMe()
				.rememberMeParameter("rememberMe")
				.key("key")
				.userDetailsService(this.userService)
				.rememberMeCookieName("remember")
				.tokenValiditySeconds(1200);
	}
}
