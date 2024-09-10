package br.com.rampagestore.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rampagestore.model.User;
import br.com.rampagestore.service.UserService;

@RestController
public class UserControl {
    
    @Autowired
    private UserService userService;

    @GetMapping("/listarUsuarios")
    public Iterable<User> catalogUsers(){
        return userService.listingUsers();
    }
}
