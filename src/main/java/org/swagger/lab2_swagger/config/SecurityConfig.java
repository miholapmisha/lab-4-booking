package org.swagger.lab2_swagger.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.swagger.lab2_swagger.service.UserSecurityService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserSecurityService userSecurityService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/user/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/bookings/**").hasRole("TOURIST")
                                .requestMatchers(HttpMethod.POST, "/activities/**", "/tour-packages/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/activities/**", "/tour-packages/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .authenticationProvider(transactionalDaoAuthenticationProvider())
                .formLogin(withDefaults())
                .logout(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TransactionalDaoAuthenticationProvider transactionalDaoAuthenticationProvider() {
        return new TransactionalDaoAuthenticationProvider(
                userSecurityService,
                passwordEncoder()
        );
    }
}
