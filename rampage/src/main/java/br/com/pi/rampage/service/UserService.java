package br.com.pi.rampage.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pi.rampage.model.User;
import br.com.pi.rampage.repository.UserRepository;

@Service
public class UserService {
    
    
    @Autowired
    private UserRepository action;

    public String registerUser(User newUser) {
        Optional<User> existingUser = action.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            return "Erro: E-mail já cadastrado!";
        }else{
            action.save(newUser);
            return "Usuário registrado com sucesso!"; 
        }
    }

}
