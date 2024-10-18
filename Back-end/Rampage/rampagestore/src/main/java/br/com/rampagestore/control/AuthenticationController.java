package br.com.rampagestore.control;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import br.com.rampagestore.config.securityconfig.TokenService;
import br.com.rampagestore.model.user.AuthenticationDTO;
import br.com.rampagestore.model.user.LoginReponseDTO;
import br.com.rampagestore.model.user.RegisterConsumerRequest;
import br.com.rampagestore.model.user.RegisterDTO;
import br.com.rampagestore.model.user.User;
import br.com.rampagestore.model.user.UserAddress;
import br.com.rampagestore.model.user.UserRole;
import br.com.rampagestore.repository.UserRepository;
import br.com.rampagestore.service.UserService;
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

    @Autowired
    private UserService userService;


    @GetMapping("/select_user_infos")
    public ResponseEntity<?> getInfosUser(@RequestPart("email") String email) {
        return userService.selectUserInfos(email);
    }
    

    @PostMapping("/login_consumer")
    public ResponseEntity<?> loginConsumer(@RequestBody @Valid AuthenticationDTO data) throws IllegalArgumentException, UnsupportedEncodingException{
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginReponseDTO(token));
    }

    //Indicar endereço padrão de cobrança
    @PutMapping("/indicate_billing_addres")
    public ResponseEntity<?> indicateBillingAddres(@RequestPart("addresId") String addresId, @RequestPart("userId") String userId) {
        long formatAddresId = Long.parseLong(addresId);
        long formatUserId = Long.parseLong(userId);
        return userService.selectBillingdAddres(formatAddresId, formatUserId);    
    }

    //Indicar endereço padrão de entrega
    @PutMapping("/indicate_standard_addres")
    public ResponseEntity<?> indicateStandardAddres(@RequestPart("addresId") String addresId, @RequestPart("userId") String userId) {
        long formatAddresId = Long.parseLong(addresId);
        long formatUserId = Long.parseLong(userId);
        return userService.selectStandardAddres(formatAddresId, formatUserId);    
    }


    //Registrar novo Endereço
    @PostMapping("/register_Addres")
    public ResponseEntity<?> registerAddres(@ModelAttribute UserAddress userAddress, @RequestPart("userId") String userId) {
        long fomartId = Long.parseLong(userId);
        return userService.registerNewAddres(userAddress, fomartId);
    }

    //Alteração da senha do cliente
    @PutMapping("/update_consumer_password")
    public ResponseEntity<?> updateConsumePassword(@RequestPart("id") String id,@RequestPart("pass") String password) {
        long fomartId = Long.parseLong(id);
        return userService.editPassword(fomartId, password);
    }


    //Alteração de dados do cliente
    @PutMapping("/update_consumer")
    public ResponseEntity<?> updateConsumer(@RequestBody RegisterDTO data) {
        return userService.editConsumer(data);
    }
    
    //Registro de cliente
    @PostMapping("/register_consumer")
    public ResponseEntity<?> registerConsumer(@RequestBody @Valid RegisterConsumerRequest request){
        System.out.println("oi");
        RegisterDTO data = request.getRegisterDTO();
        UserAddress userAddress = request.getUserAddress();
        return userService.registerNewConsumer(data, userAddress);
    }
    
    //Login do BackOffice
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) throws IllegalArgumentException, UnsupportedEncodingException{
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginReponseDTO(token));
    }

    //Registro de Funcionarios
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();
        
        UserRole role = data.role();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate userBirth = LocalDate.parse(data.birthDate(), formatter);
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), userBirth, data.cpf(), data.email(), encryptedPassword, data.gender(), role, true);
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
