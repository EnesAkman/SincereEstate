package com.EEY.SincereEstate.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->auth.requestMatchers("/img/**","/css/**").permitAll());

        http.authorizeHttpRequests(configure -> configure
                        .requestMatchers("/register","/processRegister").permitAll()
//                        .requestMatchers("/").hasRole("student")
//                        .requestMatchers("/list/**").hasRole("president")
//                        .requestMatchers("/system/**").hasRole("admin")
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                        .usernameParameter("email")
                        .loginProcessingUrl("/authenticate")
                        .defaultSuccessUrl("/",true)
                        .permitAll())
                .logout(LogoutConfigurer::permitAll);
//                .exceptionHandling(handler -> handler.accessDeniedPage("/access-denied"));
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();


    }


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        String usersByUsernameQuery = "select email, password, enabled from user where email = ?";
        String authsByUserQuery = "select email, role from roles where email = ?";

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return userDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
