package com.example.CRM.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for testing/dev
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/signup", "/api/login").permitAll()
                        .requestMatchers("/", "/create", "/save", "/customer/**").permitAll()
                        .requestMatchers("/api/**").permitAll() // 👈 Allow public access to your dashboard

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")               // custom login page URL (you'll need to create this page)
                        .defaultSuccessUrl("/profile")    // redirect here after successful login
                        .permitAll()                      // allow everyone to access login page
                )
                .logout(logout -> logout.permitAll()); // allow everyone to access logout

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Hardcoded in-memory user with username admin1 and password admin01
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("admin1@gmail.com")
                .password(passwordEncoder.encode("admin01"))
                .roles("ADMIN")
                .build());

        return manager;
    }
}
