package com.example.demo.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatchers;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private static final String USER = "USER";
	private static final String ADMIN = "ADMIN";

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void authorityManager(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
		 .usersByUsernameQuery("SELECT username, password,enabled FROM user_master WHERE username = ?")
	        .authoritiesByUsernameQuery("SELECT username, role FROM user_master WHERE username = ?");
	}

	@Bean
	SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests((req) -> req
	            .requestMatchers(HttpMethod.DELETE,"/user/{id}","/room/{id}").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.GET,"/users","/bookings").hasRole(ADMIN)
	            .requestMatchers(HttpMethod.PUT,"/room").hasRole(ADMIN)
	            .requestMatchers(HttpMethod.POST,"/room").hasRole(ADMIN)
	            
	            
	            .requestMatchers(HttpMethod.PUT, "/user").hasAnyRole(ADMIN, USER)
	            .requestMatchers(HttpMethod.GET,"/availablerooms","/rooms","/room/{id}","/booking/{id}").hasAnyRole(ADMIN, USER)
	            .requestMatchers(HttpMethod.DELETE,"/booking/{id}").hasAnyRole(ADMIN, USER)
	            .requestMatchers(HttpMethod.POST, "/booking").hasAnyRole(ADMIN, USER)
	            .requestMatchers(HttpMethod.POST,"/user").permitAll()
	            .requestMatchers(HttpMethod.GET,"user/{id}").permitAll()// Allow PUT to /user for ADMIN and USER
	            
	            .requestMatchers("/h2-console/**").permitAll()  // Allow access to H2 console
	            .requestMatchers("/swagger-ui/**").permitAll()  // Allow access to Swagger UI
	            .requestMatchers("/v3/api-docs/**").permitAll()
	            .requestMatchers("/**").permitAll()  // Allow access to other endpoints
	            .anyRequest().authenticated())  // Authenticate all other requests
//	        .csrf(csrf -> csrf
//	            .ignoringRequestMatchers(new AntPathRequestMatcher("/user", HttpMethod.POST.name()),new AntPathRequestMatcher("/h2-console/**")))  // Disable CSRF for POST requests to /user
//	        	
	        .httpBasic(withDefaults())  // Enable HTTP Basic Authentication
	        .formLogin(withDefaults());  // Enable form-based login
	    http.csrf(csrf->csrf.disable());
	    http.headers(headers->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

	    return http.build();
	}
	
	
	 @Bean
	 BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
