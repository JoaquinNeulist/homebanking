package com.mindhubbrothers.homebanking.configurations;

import com.mindhubbrothers.homebanking.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class WebConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                //aplica la configuracion de cors definida
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                //Desactiva la proteccion CSRF, la autenticacion basica HTTP y el formulario login predeterminado
                //CSRF: Cross-Site Request Forgery ()
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::disable
                        //desactiva las FrameOption, acceder console-h2
                ))
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/api/auth/login", "/api/auth/signup", "/h2-console/**").permitAll()
                                .requestMatchers( "/api/auth/current", "/api/auth/current/**", "/api/clients/current/accounts","/api/transactions", "/api/loans").hasRole("CLIENT")
                                .requestMatchers( "/api/transactions/**", "/api/clients", "/api/clients/**","/api/clients/current/accounts/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                //permite el acceso a las rutas
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                //añade el filtro Jwt antes del filtro Username...
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        //configura la politica de creacion de sesiones para que sea sin estado
                );
        return  httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(); } //codificacion de passwords

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
        return authenticationConfiguration.getAuthenticationManager(); //autenticacion en la aplicacion
    }
}
