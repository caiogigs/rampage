package br.com.pi.rampage.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*Classe Security serve para configuração de Autenticação
configuração de Sessões, Ainda não esta sendo ultilizada */ 


@Configuration//Annotation que indica que esta classe define um ou mais "Beans"
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    
    
}
