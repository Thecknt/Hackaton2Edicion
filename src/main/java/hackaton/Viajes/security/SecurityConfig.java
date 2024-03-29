package hackaton.Viajes.security;

import hackaton.Viajes.security.filters.JwtAuthenticationFilter;
import hackaton.Viajes.security.filters.JwtAuthorizationFilter;
import hackaton.Viajes.security.jwt.JwtUtils;
import hackaton.Viajes.service.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtAuthorizationFilter authorizationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                            AuthenticationManager authenticationManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/login","/createUser","/")
                        .permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/hotels","/clients","/employee")
                            .permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/createHotels","/createEmployee").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/createClients").hasAnyRole("CLIENT","EMPLOYEE");
                    auth.requestMatchers(HttpMethod.DELETE,"/hotels/{id}","clients/{id}","/employee/{id}").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/hotels/{id}", "/clients/{id}","/employee/{id}").hasAnyRole("ADMIN","EMPLOYEE");
                    auth.requestMatchers("/deleteUser").hasRole("ADMIN");
                    auth.requestMatchers("/createClient","/createUser")
                            .hasAnyRole("ADMIN","EMPLOYEE");
                    auth.requestMatchers("/helloSecured","/myPurchases").hasAnyRole("ADMIN","CLIENT");
                    auth.requestMatchers("/shoppingCart").hasAnyRole("ADMIN","CLIENT");
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session ->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                )
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

}
