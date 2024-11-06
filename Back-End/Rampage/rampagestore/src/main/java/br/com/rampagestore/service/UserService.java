package br.com.rampagestore.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.rampagestore.model.user.RegisterDTO;
import br.com.rampagestore.model.user.User;
import br.com.rampagestore.model.user.UserAddress;
import br.com.rampagestore.model.user.UserResponse;
import br.com.rampagestore.model.user.UserRole;
import br.com.rampagestore.repository.AddresRepository;
import br.com.rampagestore.repository.UserRepository;

@Service
public class UserService {


    @Autowired UserRepository userRepository;

    @Autowired AddresRepository addresRepository;

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

    //Método selecionar todos os endereços de entrega
    public ResponseEntity<?> selectAllDeliveryAdrress(Long userId) {
        List<UserAddress> addresses = addresRepository.findAllByIdUserAndDeliveryAddressTrueOrderByStandardDesc(userId);
        return ResponseEntity.ok().body(addresses);
    }

    //Método Adicionar novo endereço
    public ResponseEntity<?> registerNewAddres(UserAddress userAddress){
        // Validar endereco repetido
        // Validar se é o primeiro endereço, se sim -> standard = true
        boolean add = addresRepository.existsByIdUser(userAddress.getIdUser());
        if (!add)
            userAddress.setStandard(true);
        else
            userAddress.setStandard(false);


        userAddress.setBillingAddress(false);
        userAddress.setDeliveryAddress(true);
        userAddress.setStatus(true);

        UserAddress address = addresRepository.save(userAddress);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
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
            LocalDate userBirth = changedData.birthDate();
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
        LocalDate userBirth = data.birthDate();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), userBirth, data.cpf(), data.email(), encryptedPassword, data.gender(), UserRole.CONSUMER, true);
        this.userRepository.save(newUser);
        long userId = newUser.getId();
        userAddress.setIdUser(userId);
        userAddress.setBillingAddress(true);//Define como endereço de Cobrança
        userAddress.setDeliveryAddress(true);//Define como endereço de entrega até ser cadastrado novo endereço
        userAddress.setStatus(true);
        userAddress.setStandard(true);
        addresRepository.save(userAddress);

        return ResponseEntity.ok().build();
    }



}
