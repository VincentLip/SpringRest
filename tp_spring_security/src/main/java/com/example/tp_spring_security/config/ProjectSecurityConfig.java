package com.example.tp_spring_security.config;

import com.example.tp_spring_security.filter.JWTTokenValidator;
import com.example.tp_spring_security.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    private JWTTokenValidator jwtTokenValidator(){
        return new JWTTokenValidator();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return  daoAuthenticationProvider;

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);

                        return config;
                    }
                }).and().csrf((crsf) -> crsf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register", "/login")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authenticationProvider(authenticationProvider()).addFilterBefore(jwtTokenValidator(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/myCards").hasRole("ADMIN")
                        .requestMatchers("/myBalance").hasRole("MODERATOR")
                        .requestMatchers("/myLoans").hasRole("ADMIN")
                        .requestMatchers("/myAccount").hasRole("ADMIN")
                        .requestMatchers("/contact", "/notices", "/register", "/login").permitAll());
                /*.formLogin(Customizer.withDefaults())*/
               /* .httpBasic(Customizer.withDefaults());*/
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   /* @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .authorities("user")
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }*/

/*    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails admin = User.withUsername("mohamed")
                .password("12345")
                .authorities("admin")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }*/


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }


}
