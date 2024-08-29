package br.com.pi.rampage.view;


import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pi.rampage.model.User;
import br.com.pi.rampage.service.UserService;
import br.com.pi.rampage.service.UserService.LoginStatus;
import br.com.pi.rampage.service.validation.UserValidator;

@Component
public class Terminal {
    
    Scanner scan = new Scanner(System.in);
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;


    public void homeBackOffice(){
        String response;
        System.out.println("                              Tela principal de backoffice                                         ");  
        System.out.println("(1) Listar Produto");  
        System.out.println("(2) Listar Usuário");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.print("Entre com a opção (1 ou 2) =>");
        response = scan.nextLine().trim();
        switch (response) {
            case "1":
            listProducts();
                break;
            case "2":
            listUsers();
                break;
            default:
                break;
        }

    }

    public void listProducts(){
        
    }

    public void listUsers(){
        String response;
        System.out.println("                              Listar Usuário                                         "); 
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.print("Entre com o id para editar/ativar/inativar, 0 para voltar e i para incluir =>");  
        response = scan.nextLine().trim(); 
        switch (response) {
            case "i":
                includeUser();
                break;
            default:
                break;
        }
    }


    public boolean enter(){
        String email, password;
        LoginStatus loginStatus;
        System.out.print("Email: ");
        email = scan.nextLine().trim();
        System.out.print("Senha: ");
        password = scan.nextLine().trim();
        loginStatus = userService.login(email, password);
        switch (loginStatus) {
            case SUCCESS:
                System.out.println("Login bem-sucedido!");
               // userService.createUserSession(email);
                return true;
            case USER_INACTIVE:
                System.out.println("Login falhou. Usuário inativo.");
                break;
            case CLIENT_ACCESS_DENIED:
                System.out.println("Acesso Negado. Clientes não têm permissão para acessar esta área.");
                break;    
            default:
                System.out.println("Login falhou. Email ou senha incorretos.");
                break;
        }
        return false;
    }
    

    public void includeUser(){
        String name, cpf, email, grupo, password, response;
        System.out.println("                              Incluir usuário                                        ");
        name = userValidator.nameValidate();
        cpf = userValidator.cpfValidator();
        email = userValidator.emailValidator();
        grupo = userValidator.validateGroup();
        password = userValidator.passAble();
        System.out.println("-------------------------------------------------------------------------------------");
        User newUser = new User(name, cpf, email, password, grupo,true);
        while (true) {
            System.out.print("Salvar (Y/N) => ");
            response = scan.nextLine().trim();
            switch (response) {
                case "Y":
                try {
                    String result = userService.registerUser(newUser);
                    System.out.println(result);
                    listUsers();
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro ao registrar usuário: " + e.getMessage());
                }
                    break;    
                case "N":
                    System.out.println("Operação cancelada.");
                    listUsers();
                    break;
                default:
                    System.out.println("Opção invalida. Tente Novamente.");
                    break;
            }    
        }
        
    }


}
