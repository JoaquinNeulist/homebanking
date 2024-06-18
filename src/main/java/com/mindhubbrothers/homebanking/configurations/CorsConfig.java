package com.mindhubbrothers.homebanking.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173","https://homebanking-react.onrender.com"));
        //el link es el origen permitido para hacer solicitudes a esta aplicacion
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        //solo los metodos http dentro del array estan permitidos
        configuration.setAllowedHeaders(List.of("*"));
        //todos los headers estan permitidos
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //registra la configuracion CORS para diferentes rutas
        source.registerCorsConfiguration("/**", configuration);
        //asocia la configuracion CORS definida a todas las rutas
        return source;
    }
}
