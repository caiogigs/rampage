package br.com.rampagestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.rampagestore.model.User;
import br.com.rampagestore.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userAction;

    public Iterable<User> listingUsers(){
        return userAction.findAll();
    }
    
    public ResponseEntity<?> registerUser(User user){
        return null;

    }
}
