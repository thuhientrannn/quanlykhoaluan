/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.configs;

import com.ltjava.baitaplon.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author HIEN
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.ltjava.repository",
    "com.ltjava.service"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService userDetailService;
    
    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password");
        
        http.formLogin().defaultSuccessUrl("/").failureUrl("/login?error");

        http.formLogin().successHandler(loginSuccessHandler);
        
        http.logout().logoutSuccessUrl("/login");
        http.exceptionHandling().accessDeniedPage("/accessDenied?accessDenied");
        
        http.authorizeRequests().antMatchers("/").hasAnyAuthority("ADMIN", "MANAGER", "TEACHER", "STUDENT")
                .antMatchers("/user").hasAuthority("ADMIN")
                .antMatchers("/thesis").hasAnyAuthority("ADMIN", "MANAGER", "TEACHER")
                .antMatchers("/council").hasAnyAuthority("ADMIN", "MANAGER", "TEACHER")
                .antMatchers("/student").hasAnyAuthority("ADMIN", "MANAGER", "TEACHER", "STUDENT");
        
        http.csrf().disable();
    }
}
