package com.org.pos.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.org")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .antMatchers("/","/login*", "/resources/**", "/registration", "/", 
            		"/css/**", "/img/**", "/js/**").permitAll()
            .antMatchers("/index","/acumulado-total","/cliente-lista","/clientes-lista-detalle",
            		"control-flujo","depositos","indicador-dia","lista-tickets","ticket-ayuda")
    		.authenticated();

        http
        .csrf().disable()
        .headers().disable();
        
        
        http
        .formLogin()
            .loginPage("/login").defaultSuccessUrl("/home", true)
            .failureUrl("/login")
            .failureHandler(customAuthenticationFailureHandler);
    		//.and()
    		//.sessionManagement()
    		//.maximumSessions(1)
    		//.maxSessionsPreventsLogin(true)
    		//.expiredUrl("/login?error=Sesion expirada");
	}
	
}
