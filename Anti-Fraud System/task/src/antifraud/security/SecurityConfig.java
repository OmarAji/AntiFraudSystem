package antifraud.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final AuthenticationProvider authenticationProvider;
    private final BasicAuthenticationFilterImp basicAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/api/auth/user").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/auth/user/{username}").hasAuthority("ADMINISTRATOR")

                        .requestMatchers(HttpMethod.GET, "/api/auth/list").hasAnyAuthority("ADMINISTRATOR", "SUPPORT")

                        .requestMatchers("/actuator/shutdown").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/antifraud/transaction").hasAnyAuthority("MERCHANT")
                        .requestMatchers(HttpMethod.PUT, "/api/antifraud/transaction").hasAnyAuthority("SUPPORT")
                        .requestMatchers(HttpMethod.GET, "/api/antifraud/history").hasAnyAuthority("SUPPORT")
                        .requestMatchers(HttpMethod.GET, "/api/antifraud/history/{number}").hasAnyAuthority("SUPPORT")

                        .requestMatchers(HttpMethod.PUT, "/api/auth/access").hasAuthority("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.PUT, "/api/auth/role").hasAuthority("ADMINISTRATOR")

                        .requestMatchers(HttpMethod.POST, "/api/antifraud/suspicious-ip").hasAuthority("SUPPORT")
                        .requestMatchers(HttpMethod.DELETE, "/api/antifraud/suspicious-ip/{ip}").hasAuthority("SUPPORT")
                        .requestMatchers(HttpMethod.GET, "/api/antifraud/suspicious-ip").hasAuthority("SUPPORT")

                        .requestMatchers(HttpMethod.POST, "/api/antifraud/stolencard").hasAuthority("SUPPORT")
                        .requestMatchers(HttpMethod.DELETE, "/api/antifraud/stolencard/{cardNumber}").hasAuthority("SUPPORT")
                        .requestMatchers(HttpMethod.GET, "/api/antifraud/stolencard").hasAuthority("SUPPORT")

                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(handing -> handing
                        .authenticationEntryPoint(restAuthenticationEntryPoint)
                )

                .headers(headers -> headers.frameOptions().disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(
                        basicAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                )
                .authenticationProvider(authenticationProvider)
                .build();
    }


}
