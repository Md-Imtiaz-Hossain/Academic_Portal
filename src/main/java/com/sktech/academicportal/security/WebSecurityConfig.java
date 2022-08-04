package com.sktech.academicportal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new PortalUserDetailsService();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        // will be base on database
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // config will apply at runtime
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] staticResources = {"/css/**", "/images/**", "/fonts/**", "/scripts/**", "/plugins/**", "/frontimages/**", "/frontlayout/**",
                " /resources/**", "/js/**", "/login", "/api/**", "/", "/static/**", "/dist/css/**", "/dist/js/**", "/dist/img/**", "/dist/**"};


        http.authorizeRequests()
                .antMatchers(staticResources).permitAll()
                .antMatchers("/forgot-password", "/change-password").permitAll()
                .antMatchers("/users/**").hasAnyAuthority("Admin")
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/after-login-dashboard", true)
                .usernameParameter("email") //default parameter to login
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .permitAll()
                .and().rememberMe().key("AbcDefgHijKlmnOpqrs_1234567890") //this will be create jsession id (cookies) when login
                .tokenValiditySeconds(7 * 24 * 60 * 60);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/scripts/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/webjars/**");


    }


}
