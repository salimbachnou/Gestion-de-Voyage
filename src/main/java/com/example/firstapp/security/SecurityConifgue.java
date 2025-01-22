package com.example.firstapp.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConifgue {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/voyages", "/contact", "/login1", "/register", "/error", "/css/**", "/js/**").permitAll()
				.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers("/voyages/*/reserver").hasAnyAuthority("ROLE_USER", "ROLE_CLIENT")
				.requestMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_CLIENT")
				.requestMatchers("/reservations/**").hasAnyAuthority("ROLE_USER", "ROLE_CLIENT")
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login1")
				.loginProcessingUrl("/login1")
				.defaultSuccessUrl("/login-success", true)
				.failureUrl("/login1?error=true")
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.permitAll()
			)
			.exceptionHandling(ex -> ex
				.accessDeniedPage("/error/403")
			);
		
		return http.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
}
