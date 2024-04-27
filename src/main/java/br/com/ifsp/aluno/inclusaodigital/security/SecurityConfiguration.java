package br.com.ifsp.aluno.inclusaodigital.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    private final SecurityFilterInterlocutor securityFilterInterlocutor;

    public SecurityConfiguration(SecurityFilterInterlocutor securityFilterInterlocutor) {
        this.securityFilterInterlocutor = securityFilterInterlocutor;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorization) ->
                    authorization.requestMatchers(HttpMethod.POST, "/interlocutor").permitAll()
                            .requestMatchers(HttpMethod.POST, "/interlocutor/auth").permitAll()
                            .anyRequest().authenticated())
                .addFilterBefore(securityFilterInterlocutor, AuthorizationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
