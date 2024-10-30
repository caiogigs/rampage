package br.com.rampagestore.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import br.com.rampagestore.model.user.RegisterDTO;
import br.com.rampagestore.model.user.User;
import br.com.rampagestore.model.user.UserAddress;
import br.com.rampagestore.model.user.UserResponse;
import br.com.rampagestore.model.user.UserRole;
import br.com.rampagestore.repository.AddresRepository;
import br.com.rampagestore.repository.UserRepository;
import br.com.rampagestore.valid.Validation;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired 
    private UserRepository userRepository;

    @Autowired 
    private AddresRepository addresRepository;

    @Autowired
    private Validation validation;

    //Método para o usuário visualizar as proprias informações
    public ResponseEntity<?> selectUserInfos(String email){
        User loggedUser = (User) userRepository.findByEmail(email);
        List<UserAddress> userAddresses = addresRepository.findAllByIdUser(loggedUser.getId());
        UserResponse userResponse = new UserResponse(loggedUser, userAddresses);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


    //Método selecionar qual é o endereço de cobrança
    public ResponseEntity<?> selectBillingdAddres(long addresId, long userId){
        List<UserAddress> addresses = addresRepository.findAllByIdUser(userId);
        if (!addresses.isEmpty()) {
            for (UserAddress usAd : addresses) {
                usAd.setBillingAddress(false);
                addresRepository.save(usAd);
            }
        }
        UserAddress billingUserAddress = addresRepository.findById(addresId);
        billingUserAddress.setBillingAddress(true);
        addresRepository.save(billingUserAddress);
        return ResponseEntity.ok().build();
    }

    //Método selecionar qual é o endereço de entrega padrão
    public ResponseEntity<?> selectStandardAddres(long addresId, long userId){
        List<UserAddress> addresses = addresRepository.findAllByIdUser(userId);
        if (!addresses.isEmpty()) {
            for (UserAddress usAd : addresses) {
                usAd.setDeliveryAddress(false);
                addresRepository.save(usAd);
            }
        }
        UserAddress standardUserAddress = addresRepository.findById(addresId);
        standardUserAddress.setDeliveryAddress(true);
        addresRepository.save(standardUserAddress);
        return ResponseEntity.ok().build();
    }


    //Método Adicionar novo endereço
    public ResponseEntity<?> registerNewAddres(UserAddress userAddress, long userId){
        List<UserAddress> addresses = addresRepository.findAllByIdUser(userId);
        if (!addresses.isEmpty()) {
            for (UserAddress usAd : addresses) {
                usAd.setDeliveryAddress(false);
                addresRepository.save(usAd);
            }
        }
        userAddress.setIdUser(userId);
        userAddress.setBillingAddress(false);
        userAddress.setDeliveryAddress(true);
        userAddress.setStatus(true);
        addresRepository.save(userAddress);
        return ResponseEntity.ok().build();
    }


    //Metodo de mudar a senha
    public ResponseEntity<?>editPassword(Long id, String password){
        Optional<User> optionalUser = userRepository.findById(id);
        String newPassword = new BCryptPasswordEncoder().encode(password);
        User changedUser = optionalUser.get();
        changedUser.setPassword(newPassword);
        userRepository.save(changedUser);
        return ResponseEntity.ok().build();
    }
    
    //Método para editar dados do cliente
    public ResponseEntity<?> editConsumer(RegisterDTO changedData){
        String email = changedData.email();
        User existingUser = (User) userRepository.findByEmail(email);
        if (existingUser!= null) {
           User unchangedData = (User) existingUser;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate userBirth = LocalDate.parse(changedData.birthDate(), formatter);
            // Atualizando somente os campos editáveis
            unchangedData.setName(changedData.name());
            unchangedData.setBirthDate(userBirth);
            unchangedData.setGender(changedData.gender());
            // Salvar as alterações
           userRepository.save(unchangedData);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //Método de Cadastro de cliente e endereço de cobrança 
    public ResponseEntity<?> registerNewConsumer(RegisterDTO data, UserAddress userAddress){
        if(this.userRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();
        if(this.userRepository.findByCpf(data.cpf()) != null){
            return ResponseEntity.badRequest().build();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate userBirth = LocalDate.parse(data.birthDate(), formatter);
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), userBirth, data.cpf(), data.email(), encryptedPassword, data.gender(), UserRole.CONSUMER, true);
        this.userRepository.save(newUser);
        long userId = newUser.getId();
        userAddress.setIdUser(userId);
        userAddress.setBillingAddress(true);//Define como endereço de Cobrança
        userAddress.setDeliveryAddress(true);//Define como endereço de entrega até ser cadastrado novo endereço
        userAddress.setStatus(true);
        addresRepository.save(userAddress);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> registerUserBackOffice(RegisterDTO data){
        if(this.userRepository.findByEmail(data.email()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Já existe um usuário registrado com este Email"));
        }
        if(this.userRepository.findByCpf(data.cpf()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Já existe um usuário registrado com este CPF"));     
        }

        UserRole role = data.role();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate userBirth = LocalDate.parse(data.birthDate(), formatter);
        String userCPF = data.cpf().replaceAll("\\D", "");
        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.name(), userBirth, userCPF, data.email(), encryptedPassword, data.gender(), role, true);
        this.userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "Cadastro realizado com sucesso!"));
    }



    //Método de Atualizar usuários do BackOffice
    public ResponseEntity<?> updateUsersBackOffice(long id, RegisterDTO data) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(data.name());
            user.setCpf(data.cpf());
    
            if (data.password() != null && !data.password().isEmpty()) {
                String encryptedPassword = passwordEncoder.encode(data.password());
                user.setPassword(encryptedPassword);
            }
    
            // Verifica se o usuário logado é o mesmo que está sendo atualizado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserEmail = authentication.getName();
            String message = "Usuário atualizado com sucesso";
            if (user.getEmail().equals(currentUserEmail)) {
                message+= ". Exceto o grupo, pois o usuário está logado no momento.";
            } else {
                user.setRole(data.role());
            }
            userRepository.save(user);
            return ResponseEntity.ok(Collections.singletonMap("message", message));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }
    
}
