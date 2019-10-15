package com.herokuapp.schoolmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.herokuapp.schoolmvc.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    UserDetailsServiceImpl userDetailsService;
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
     
     
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.csrf().disable();
 
        http.authorizeRequests().antMatchers("/profile", "/dashboard").authenticated();

        http.authorizeRequests().antMatchers("/admin").access("hasAuthority('ADMIN')");

        http.authorizeRequests().antMatchers("/users", "/user/**", "/employee/**", "/register").access("hasAuthority('USER_MANAGER')");

        http.authorizeRequests().antMatchers("/course/**").access("hasAuthority('COURSE_MANAGER')");

        http.authorizeRequests().antMatchers("teacher/**", "/notification/**", "/declare-result/**").access("hasAuthority('TEACHER')");

        http.authorizeRequests().antMatchers("/course-page/**").access("hasAnyAuthority('TEACHER', 'STUDENT')");

        http.authorizeRequests().antMatchers("/my-salaries").access("hasAuthority('EMPLOYEE')");

        http.authorizeRequests().antMatchers("/my-fees").access("hasAuthority('STUDENT')");

        http.authorizeRequests().antMatchers("/fees", "/receive-fees").access("hasAuthority('ACCOUNT_MANAGER')");

        http.authorizeRequests().antMatchers("/salaries", "/dispatch-salary").access("hasAuthority('ACCOUNT_MANAGER')");

 
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        http.authorizeRequests()
            .and()
            .formLogin()
            .loginProcessingUrl("/j_spring_security_check")
            .loginPage("/login")
            .defaultSuccessUrl("/dashboard")
            .failureUrl("/login?error=true")
            .usernameParameter("username")
            .passwordParameter("password")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/");
 
    }
}