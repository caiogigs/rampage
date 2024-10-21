package br.com.rampagestore.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.rampagestore.config.CorsConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Autowired
    CorsConfig corsConfig; 

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource())) 
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
            .requestMatchers(HttpMethod.POST,"/auth/login_consumer").permitAll()
            .requestMatchers(HttpMethod.PUT,"/auth/update_consumer").permitAll()
            .requestMatchers(HttpMethod.PUT,"/auth/update_consumer_password").permitAll()
            .requestMatchers(HttpMethod.PUT,"/auth/indicate_standard_addres").permitAll()
            .requestMatchers(HttpMethod.PUT,"/auth/indicate_billing_addres").permitAll()
            .requestMatchers(HttpMethod.POST,"/auth/register_consumer").permitAll()
            .requestMatchers(HttpMethod.POST,"/auth/register_Addres").permitAll()
            .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
            .requestMatchers(HttpMethod.POST,"/register_Product").permitAll()
            .requestMatchers(HttpMethod.GET,"/auth/listarUsuarios").permitAll()
            .requestMatchers(HttpMethod.GET,"/auth/nomeContem").permitAll()
            .requestMatchers(HttpMethod.POST,"/auth/mudarStatus").permitAll()
            .requestMatchers(HttpMethod.GET,"/todos_produtos").permitAll()
            .requestMatchers(HttpMethod.GET,"/olhar_produto/{id}").permitAll()
            .requestMatchers(HttpMethod.GET,"/selecionar_produto/{id}").permitAll()
            .requestMatchers(HttpMethod.GET,"/listar_produto").permitAll()
            .requestMatchers(HttpMethod.GET,"/auth/select_user_infos").permitAll()
            .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration ) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
