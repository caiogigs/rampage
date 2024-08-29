package br.com.pi.rampage.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.pi.rampage.model.User;
import br.com.pi.rampage.repository.UserRepository;
//import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
    
    
    @Autowired
    private UserRepository action;

    @Autowired
    private PasswordEncoder passwordEncoder;

  //  @Autowired
  //  private HttpSession httpSession; 

    public enum LoginStatus {
        SUCCESS,
        FAILURE,
        USER_INACTIVE,
        CLIENT_ACCESS_DENIED;
    }

    //public void createUserSession(String email){
    //    Optional<User> loggedInUser = action.findByEmail(email);
    //    httpSession.setAttribute(loggedInUser.get().getNome(), loggedInUser.get().getGrupo());
  //  }

    public String registerUser(User newUser) {
        Optional<User> existingUser = action.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            return "Erro: E-mail já cadastrado!";
        }else{
            action.save(newUser);
            return "Usuário registrado com sucesso!"; 
        }
    }

    public LoginStatus login(String email, String password) {
        Optional<User> userOptional = action.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getSenha())) {
                if (user.isStatus()) {
                    String group = user.getGrupo(); 
                    if ("cliente".equals(group)) {
                        return LoginStatus.CLIENT_ACCESS_DENIED;
                    }
                    return LoginStatus.SUCCESS;
                }
                return LoginStatus.USER_INACTIVE;
            }
        }
        return LoginStatus.FAILURE;
    }
}
