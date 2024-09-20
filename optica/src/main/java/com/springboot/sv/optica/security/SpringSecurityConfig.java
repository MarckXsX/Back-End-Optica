package com.springboot.sv.optica.security;

import com.springboot.sv.optica.security.filter.JwtAuthenticationFilter;
import com.springboot.sv.optica.security.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.GET, "/api/expedientes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/expedientes/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/expedientes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/expedientes/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/pacientes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/pacientes/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/pacientes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/pacientes/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/pacientes/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/citas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/citas/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/citas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/citas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/citas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/consultas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/consultas/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/consultas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/consultas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/recetas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/recetas/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/recetas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/recetas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/recetas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/medicamentos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medicamentos/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/medicamentos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/medicamentos/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/medicamentos/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/especialidades").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/especialidades/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/especialidades").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/especialidades/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/especialidades/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/doctores").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/doctores/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/doctores").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/doctores/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/doctores/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/graduaciones").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/graduaciones/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/graduaciones").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/graduaciones/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/aros").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/aros/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/aros").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/aros/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/aros/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/facturaC/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/facturaC").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/facturaC").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/facturasP").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/facturasP/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/facturasP").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .cors(cors-> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }
}
