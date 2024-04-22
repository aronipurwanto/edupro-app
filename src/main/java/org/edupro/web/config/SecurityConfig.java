package org.edupro.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig {
    private String[] WHITE_LIST_URL = new String[]{
            // "/**",
            // "/master/lookup/**"
    };

     private final KeycloakLogoutHandler keycloakLogoutHandler;

     @Bean
     protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
         return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
     }

     @Order(1)
     @Bean
     public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {
         http
                 .authorizeHttpRequests(req ->
                     req.requestMatchers(WHITE_LIST_URL).permitAll()
                             .anyRequest().authenticated()

                 ).oauth2Login(Customizer.withDefaults())
                 .logout(logout -> logout.addLogoutHandler(keycloakLogoutHandler)
                         .logoutSuccessUrl("/"));
         /*
         http.authorizeRequests()
                 .requestMatchers(new AntPathRequestMatcher("/"))
                 .permitAll()
                 .anyRequest()
                 .authenticated();
         http.oauth2Login()
                 .and()
                 .logout()
                 .addLogoutHandler(keycloakLogoutHandler)
                 .logoutSuccessUrl("/");

          */
         return http.build();
     }

    @Order(2)
    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests( req ->
                req.requestMatchers(new AntPathRequestMatcher("/customers*"))
                        .hasRole("USER")
                        .anyRequest().authenticated()
                );
        http.oauth2ResourceServer((oauth2) -> oauth2
                .jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }
}
