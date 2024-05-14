package com.upc.api_examen_parcial_202120632.config;
import com.upc.api_examen_parcial_202120632.security.JwtAuthenticationEntryPoint;
import com.upc.api_examen_parcial_202120632.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint rgpaJwtAuthenticationEntryPoint;


    @Autowired
    private UserDetailsService rpgaJwtUserDetailsService;

    @Autowired
    private JwtRequestFilter rpgaJwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration rpgaAuthenticationConfiguration) throws Exception {
        return rpgaAuthenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder rpgaAuth) throws Exception {
        rpgaAuth.userDetailsService(rpgaJwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity rpgaHttp) throws Exception {
        rpgaHttp.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                authorize.requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**",
                        "/error",
                        "/favicon.ico").permitAll();
                authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        rpgaHttp.exceptionHandling( exception -> exception
                    .authenticationEntryPoint(rgpaJwtAuthenticationEntryPoint));

        rpgaHttp.addFilterBefore(rpgaJwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return rpgaHttp.build();
    }
}
