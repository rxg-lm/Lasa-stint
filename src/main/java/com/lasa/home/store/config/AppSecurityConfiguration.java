package com.lasa.home.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
public class AppSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityConfigure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(req ->
                        req.requestMatchers("/login").authenticated()
                                .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
