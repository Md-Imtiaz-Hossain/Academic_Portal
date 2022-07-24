package com.sktech.academicportal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        // to make allow public access without authentication
        /*http.headers().frameOptions().sameOrigin();
        http.csrf().disable();*/


        http.authorizeRequests()
                .antMatchers("/users/**").hasAnyAuthority("Admin")
                .antMatchers("/upload/**").hasAnyAuthority("Admin", "Editor")
                .antMatchers("/categories/**", "/brands/**").hasAnyAuthority("Admin", "Editor")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/after-login-dashboard", true)
                .usernameParameter("email") //default parameter to login
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/")
                .and().rememberMe().key("AbcDefgHijKlmnOpqrs_1234567890") //this will be create jsession id (cookies) when login
                .tokenValiditySeconds(7 * 24 * 60 * 60);


//        http.authorizeRequests().anyRequest().permitAll();

//        form th:action="@{/logout} will automatic map with spring authen logout?
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**").antMatchers("/js/**").antMatchers("/dist/**");
//        web.ignoring()
//                .antMatchers("/h2-console/**")
//                .antMatchers("/images/**", "/js/**", "/webjars/**");

    }

    //configure(AuthenticationManagerBuilder auth) -> daoAuthenticationProvider -> use bean UserDetailsService userDetailsService()
    // -> configure(HttpSecurity http) -> configure(WebSecurity web)
    // -> khi loggin thi lay info tu authenticationProvider
    // sau do lay data ShopmeUserDetailsService#loadUserByUsername will invoke -> pass username/password tu user fromdb
    // then check
}
