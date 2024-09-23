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
            .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
            .requestMatchers(HttpMethod.POST,"/cadastrar_novo_produto").permitAll()
            .requestMatchers(HttpMethod.PUT,"/atualizar_produto").permitAll()
            .requestMatchers(HttpMethod.GET,"/auth/listarUsuarios").permitAll()
            .requestMatchers(HttpMethod.GET,"/listarProduto").permitAll()
            .requestMatchers(HttpMethod.GET,"/listar_produto_recente").permitAll()
            .requestMatchers(HttpMethod.GET,"/produtos_contem_palavra").permitAll()
            .requestMatchers(HttpMethod.PUT, "/mudar_status_produto").permitAll()
            .requestMatchers(HttpMethod.PUT,"/aumentar_quantidade_produto").permitAll()
            .requestMatchers(HttpMethod.PUT,"/diminuir_quantidade_produto").permitAll()
            .requestMatchers(HttpMethod.GET,"/auth/nomeContem").permitAll()
            .requestMatchers(HttpMethod.POST,"/auth/mudarStatus").permitAll()
            
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
