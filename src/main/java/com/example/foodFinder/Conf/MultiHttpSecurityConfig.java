package com.example.foodFinder.Conf;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class MultiHttpSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/signup/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(
                "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**",
                "/resources/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**",
                "/images/**", "/scss/**", "/vendor/**", "/favicon.ico", "/auth/**", "/favicon.png",
                "/v2/api-docs", "/configuration/ui", "/configuration/security", "/swagger-ui.html",
                "/webjars/**", "/swagger-resources/**", "/swagge‌​r-ui.html", "/actuator",
                "/actuator/**"
        );
    }
}

