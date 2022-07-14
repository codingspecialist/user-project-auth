package com.getinthere.evaluate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.getinthere.evaluate.handler.LoginSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/emp/**")
                .access("hasRole('ROLE_EMP') or hasRole('ROLE_ADMIN')")
                .antMatchers("/teacher/**")
                .access("hasRole('ROLE_TEACHER')  or hasRole('ROLE_EMP') or hasRole('ROLE_ADMIN')")
                .antMatchers("/student/**")
                .access("hasRole('ROLE_STUDENT') or hasRole('ROLE_TEACHER')  or hasRole('ROLE_EMP') or hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/login")
                .successHandler(new LoginSuccessHandler());

    }
}