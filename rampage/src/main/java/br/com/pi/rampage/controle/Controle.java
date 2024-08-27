package br.com.pi.rampage.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.pi.rampage.model.User;
import br.com.pi.rampage.repositorio.Repositorio;

@RestController
public class Controle {
   
    @Autowired
    private Repositorio acao;

    
    public User registrar(User newUser){
        return acao.save(newUser);
    }

}
