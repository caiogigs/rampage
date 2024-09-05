package br.com.pi.rampage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.pi.rampage.model.User;
import br.com.pi.rampage.repository.UserRepository;



/*Classe Service encapsulamento da Lógica de Negócios
Se conecata com a classe UserRepository, UserValidation e
Terminal para auxiliar as Operações de CRUD relacionadas a classe User e tabela Users 
*/
@Service
public class UserService {
    
    @Autowired//Injeção Automatica de Dependâncias 
    private UserRepository action;

    @Autowired//Injeção Automatica de Dependâncias 
    private PasswordEncoder passwordEncoder;


    //Variaveis enum definem o status do login
    public enum LoginStatus {
        SUCCESS,
        FAILURE,
        USER_INACTIVE,
        ACCESS_DENIED;
    }

    //Método de cadastro de novos usuários
    public String registerUser(User newUser) {
        Optional<User> existingUser = action.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            return "Erro: E-mail já cadastrado!";
        }else{
            action.save(newUser);
            return "Usuário registrado com sucesso!"; 
        }
    }

    //Método atualização de dados dos usuários 
    public void updateUser(User user){
        action.save(user);
    }

    //Método de login
    public LoginStatus login(String email, String password) {
        Optional<User> userOptional = action.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getSenha())) {
                if (user.isStatus()) {
                    String group = user.getGrupo(); 
                    if (!"administrador".equalsIgnoreCase(group) && !"estoquista".equalsIgnoreCase(group)) {
                        return LoginStatus.ACCESS_DENIED;
                    }
                    UserSession.setUserSession(user.getEmail(), user.getGrupo());
                    return LoginStatus.SUCCESS;
                }
                return LoginStatus.USER_INACTIVE;
            }
        }
        return LoginStatus.FAILURE;
    }
   

    //Método cria a tabela para listagem dos usuários
    public String userTable(List<User> users){
        StringBuilder table = new StringBuilder(); 
        users = action.findAll();
        table.append(String.format("%-10s|%-20s|%-50s|%-15s|%-15s","codigo", "nome", "email", "status", "grupo"));
        table.append("\n");
        table.append("-".repeat(10)+"+"+"-".repeat(20)+"+"+"-".repeat(50)+"+"+"-".repeat(15)+"+"+"-".repeat(15));
        table.append("\n");

        for(User user : users){
            String status = user.isStatus() ? "Ativo" : "Inativo";
            table.append(String.format("%-10s|%-20s|%-50s|%-15s|%-15s",
                user.getCodigo(),
                user.getNome(),
                user.getEmail(),
                status,
                user.getGrupo()));
            table.append("\n");    
        }
        return table.toString();
    }

    //Método de busca de usúario pelo ID
    public User selectUserById(int id){
        Optional<User> user =action.findByCodigo(id);
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }


    //Método que verifica se o grupo do usuário é Administrador
    public boolean isAdmin() {
        String userGroup = UserSession.getUserGroup(); //Obtém o grupo do usuário diretamente da sessão
        return "ADMINISTRADOR".equalsIgnoreCase(userGroup);
    }
    
}
