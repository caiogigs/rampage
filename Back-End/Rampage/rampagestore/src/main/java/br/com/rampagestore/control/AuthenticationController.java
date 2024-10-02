package br.com.rampagestore.control;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rampagestore.config.securityconfig.TokenService;
import br.com.rampagestore.model.user.AuthenticationDTO;
import br.com.rampagestore.model.user.LoginReponseDTO;
import br.com.rampagestore.model.user.RegisterDTO;
import br.com.rampagestore.model.user.User;
import br.com.rampagestore.model.user.UserRole;
import br.com.rampagestore.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) throws IllegalArgumentException, UnsupportedEncodingException{
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginReponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();
        
        UserRole role = data.role();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.cpf(), data.email(), encryptedPassword, role, true);

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
        
    }

    @GetMapping("/listarUsuarios")
    public Iterable<User> listingUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/nomeContem")
    public Iterable<User> nameContain(@RequestParam String term) {
        return userRepository.findByNameContaining(term);
    }

    @PostMapping("/mudarStatus")
    public ResponseEntity<?> changeStatus(@RequestBody User user) {
        // Busca o usuário pelo email
        User changeUser = (User) userRepository.findByEmail(user.getEmail());
        if (changeUser == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o usuário não for encontrado
        }
    
        // Atualiza o status do usuário
        changeUser.setStatus(!changeUser.isStatus());
        userRepository.save(changeUser); // Salva o usuário atualizado
    
        return ResponseEntity.ok().build(); // Retorna 200 OK
    }
    
    
    
    

}
