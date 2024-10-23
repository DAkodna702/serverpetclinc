package com.serverpet.server.Security;


import com.serverpet.server.Services.UserServiceImpl;
import com.serverpet.server.Util.JwtServicie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtServicie  jwtUtils;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationProvider authenticationProvider) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // EndPoints publicos de Users
                    http.requestMatchers(HttpMethod.POST, "/auth/user/log-in").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/auth/user/sign-up").permitAll();
                    // EndPoints publicos de workers
                    http.requestMatchers(HttpMethod.POST, "/auth2/worker/log-in").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/auth2/worker/sign-up").permitAll();

                    // EndPoints UserPrivados
                    http.requestMatchers(HttpMethod.PUT, "/auth/user/updateuser").hasAnyRole("Administrador","Custumer");
                    http.requestMatchers(HttpMethod.DELETE, "/auth/user/deleteuser/**").hasAnyRole("Administrador","Custumer");
                    http.requestMatchers(HttpMethod.GET, "/auth/user/all").hasAnyRole("Administrador");

                    //EndPoints WorkerPrivados
                    http.requestMatchers(HttpMethod.PUT, "/auth2/worker/updateuser").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.DELETE, "/auth2/worker/deleteuser/**").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.GET, "/auth2/worker/all").hasAnyRole("Administrador");

                    //EndPoints MascotPrivados
                    http.requestMatchers(HttpMethod.GET, "/mascot/user/**").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.POST, "/mascot/create").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.PUT, "/mascot/update/**").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.DELETE, "/mascot/delete/**").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.GET, "/mascot/listmascot").hasAnyRole("Administrador");

                    //EndPoints HistoriPrivados
                    http.requestMatchers(HttpMethod.GET, "/hist/mascotP/**").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.GET, "/hist/mascotT/**").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.GET, "/hist/workerP/**").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.GET, "/hist/workerT/**").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.POST, "/hist/save").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.GET, "/hist/all").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.PUT, "/hist/updateC/**").hasAnyRole("Administrador");

                    //Enpoint Factura
                    http.requestMatchers(HttpMethod.POST, "/facturas/crear").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.GET, "/facturas/list").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.GET, "/facturas/pdf/**").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.GET, "/facturas/reporte/**").hasAnyRole("Administrador");
                    http.requestMatchers(HttpMethod.PUT, "/facturas/actualizar/**").hasAnyRole("Administrador");



                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new Filter(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
