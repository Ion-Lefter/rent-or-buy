package com.rent.house.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtRequestFilter) throws Exception {
        http.authorizeHttpRequests((authz) -> authz.requestMatchers("/api/auth/login")
                .permitAll()
//                .requestMatchers(HttpMethod.GET)
//                .permitAll()
//                .requestMatchers(HttpMethod.DELETE)
//                .hasAuthority(ADMIN)
//                .requestMatchers(HttpMethod.POST)
//                .permitAll()
//                .requestMatchers(HttpMethod.PUT)
//                .hasAuthority(USER)
                .anyRequest()
                .authenticated());

        //http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling((exceptions) -> exceptions.authenticationEntryPoint(authEntryPoint));
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(c -> c.disable());

        // Add the JWT filter to the Spring Security filter chain
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.exceptionHandling((exceptions) -> exceptions.authenticationEntryPoint(authEntryPoint));
//        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.csrf(c -> c.disable());
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(
//                (authz) -> authz.requestMatchers("/api/**")
//                        .permitAll()
//                        .requestMatchers(HttpMethod.GET)
//                        .permitAll()
//                        .requestMatchers(HttpMethod.PUT)
//                        .hasAuthority(USER)
//                        .requestMatchers(HttpMethod.POST)
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated());
//        http.csrf(c -> c.disable());
//        return http.build();
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }


}