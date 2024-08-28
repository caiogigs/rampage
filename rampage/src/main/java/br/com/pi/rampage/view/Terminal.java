package br.com.pi.rampage.view;


import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pi.rampage.model.User;
import br.com.pi.rampage.service.UserService;
import br.com.pi.rampage.service.validation.UserValidator;

@Component
public class Terminal {
    
    Scanner scan = new Scanner(System.in);
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;


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
                    return;
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro ao registrar usuário: " + e.getMessage());
                }
                    break;    
                case "N":
                    System.out.println("Operação cancelada.");
                    return;
                default:
                    System.out.println("Opção invalida. Tente Novamente.");
                    break;
            }    
        }
        
    }


}
