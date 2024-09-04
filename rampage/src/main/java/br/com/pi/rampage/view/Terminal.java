package br.com.pi.rampage.view;


import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pi.rampage.model.User;
import br.com.pi.rampage.service.UserService;
import br.com.pi.rampage.service.UserService.LoginStatus;
import br.com.pi.rampage.service.UserSession;
import br.com.pi.rampage.service.validation.UserValidator;


//Classe responsavel em interagir diretamente com o Usuario
@Component
public class Terminal {
    
    Scanner scan = new Scanner(System.in);
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

//Tela principal do BackOffice
    public void homeBackOffice(){
        String response;
        System.out.println("                                                             Tela principal de backoffice                                         ");  
        System.out.println("(1) Listar Produto");  
        System.out.println("(2) Listar Usuários");
        System.out.println("(0) Loggout");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        while (true) {
            System.out.print("Entre com a opção (1, 2 ou 0) =>");
            response = scan.nextLine().trim();
            switch (response) {
                case "1":
                    readUserInput("(1) Listar Produto");
                    listProducts();
                    break;
                case "2":
                if (userService.isAdmin()) {
                    readUserInput("(2) Listar usuarios");
                    listUsers();
                } else {
                    readUserInput("(2) Listar usuarios");
                    System.out.println("Acesso negado. Apenas Administradores podem listar usuários.");
                    homeBackOffice();
                    break;
                }
                case "0":
                    UserSession.clearSession();
                    readUserInput("(0) Loggout");
                    System.out.println("Saindo....");
                    start();;
                    break;
                default:
                    System.out.println("Entrada inválida. Por favor, insira uma das opções listadas.");
                    break;
            }    
        }
        

    }

    //Método para listar produtos
    public void listProducts(){
        System.out.println("Não ha produtos para listas");        
    }

    //Tela com lista de usuários e opções
    public void listUsers() {
        String response;
        System.out.println("                                              Listar Usuários"); 
        System.out.println(userService.userTable(null));
        System.out.print("Entre com o id para editar/ativar/inativar, 0 para voltar e i para incluir =>");  
        response = scan.nextLine().trim(); 
        switch (response) {
            case "i":
                readUserInput("(i) Listar produto");
                includeUser();
                break;
            case "0":
                readUserInput("(0) Voltar");
                homeBackOffice();
                break;    
            default:
                if (userValidator.isNumeric(response)) {
                    int userId = Integer.parseInt(response);
                    User user = userService.selectUserById(userId);
                    if (user != null) {
                        readUserInput("ID:"+response);
                        editUser(user);
                    } else {
                        System.out.println("ID inválido. Usuário não encontrado.");
                        listUsers(); 
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número ou uma das opções listadas.");
                    listUsers(); 
                }
                break;
        }
    }

    //Tela de edição do usuário
    public void editUser(User user){
        System.out.println("\n");
        System.out.println("                                                     Opção de edição de usuário");
        System.out.println(user);
        System.out.println(generateLineSeparator(140));
        System.out.print("Opções:\n"
            + "1) Alterar usuário\n"
            + "2) Alterar senha\n"
            + "3) Ativar/Desativar\n"
            + "4) Voltar listar usuários\n"
            +"Entre com a opção (1,2,3,4) =>");
        while (true){
            String response = scan.nextLine().trim();
            switch (response) {
                case "1":
                    readUserInput("(1) Alterar usuário");
                    updateUser(user);
                    break;
                case "2":
                    readUserInput("(2) Alterar senha");
                    updatePassword(user);
                    break;
                case "3":
                    readUserInput("(3) Ativar/Desativar");
                    activateUser(user);
                    break;    
                case "4":
                    readUserInput("(4) Voltar listar usúarios");
                    listUsers();
                    break;        
                default:
                    break;
            }    
        }
       
    }

    //Tela para atualizar a senha do usuario
    public void updatePassword(User user){
        System.out.println("                                                              Alterar senha");
        System.out.println(user);
        System.out.println(generateLineSeparator(140));
        System.out.println("\n");
        user.setSenha(userValidator.passAble("Nova Senha =>"));
        while (true) {
            System.out.print("Salvar (Y/N) => ");
            String response = scan.nextLine().trim().toLowerCase();;
            switch (response) {
                case "y":
                try{
                    userService.updateUser(user);
                    readUserInput("(Y) Salvar");
                    System.out.println("Senha alterada com sucesso!");
                    listUsers();
                    break;    
                }catch (IllegalArgumentException e) {
                    System.out.println("Erro ao senha do usuário: " + e.getMessage());
                }
                    break;
                case "n":
                    readUserInput("(N) Cancelar");    
                    System.out.println("Operação cancelada.");
                    listUsers();
                    break;
                default:
                    System.out.println("Opção invalida. Tente Novamente.");
                    break;
            }
            
        }
    }


    //Tela para atualizar informações do usuário
    public void updateUser(User user){
        System.out.println("                                                             Alterar usuário");
        System.out.println(user);
        System.out.println(generateLineSeparator(140));
        user.setNome(userValidator.nameValidate());
        user.setCpf(userValidator.cpfValidator());
        user.setGrupo(userValidator.validateGroup());
        userService.updateUser(user);
        while (true) {
            System.out.print("Salvar (Y/N) => ");
            String response = scan.nextLine().trim().toLowerCase();;
            switch (response) {
                case "y":
                try {
                    userService.updateUser(user);
                    readUserInput("(Y) Salvar");
                    System.out.println("Usuario alterado com sucesso!");
                    listUsers();
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro ao alterar usuário: " + e.getMessage());
                }
                    break;
                case "n":
                    readUserInput("(N) Cancelar");
                    System.out.println("Operação cancelada.");
                    listUsers();
                    break;
                default:
                    System.out.println("Opção invalida. Tente Novamente.");
                    break;
            }    
        }

    }

    //Tela de login
    public boolean enter(){
        String email, password;
        LoginStatus loginStatus;
        System.out.println("\n");
        System.out.println("                                                             Login");
        System.out.println("\n"+"\n");
        System.out.print("Email: ");
        email = scan.nextLine().trim();
        System.out.print("Senha: ");
        password = scan.nextLine().trim();
        loginStatus = userService.login(email, password);
        while (true) {
            switch (loginStatus) {
                case SUCCESS:
                    System.out.println("Login bem-sucedido!");
                    homeBackOffice();
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
        }
    }
    
    //Tela inicial do sistema
    public void start() {
        while (true) {
            System.out.println("\n");
            System.out.println("                                                 Bem-vindo ao Back-Office RampageStore!");
            System.out.println("\n"+"\n");
            System.out.println("(1) Fazer Login");
            System.out.println("(2) Sair");
            System.out.print("Escolha uma opção: ");
            String response = scan.nextLine().trim();
            switch (response) {
                case "1":
                    readUserInput("(1) Fazer Login");
                    if (enter()) {
                        homeBackOffice(); 
                    }
                    break;
                case "2":
                    readUserInput("(2) Sair.");
                    System.out.println("Encerrando o sistema...");
                    System.exit(0); 
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    //Tela para ativar/desativar usuarios
    public void activateUser(User user){
        System.out.println("                                        Ativar/Desativar usuário");
        System.out.println(user);
        System.out.println(generateLineSeparator(140));
        System.out.println("\n");
        if(user.isStatus()){
            System.out.println("\n"+"<<Desativar usuário>>");
        }else{
            System.out.println("\n"+"<<Ativar usuário>>");
        }
        while (true) { 
            System.out.print("\n"+"Salvar alteração (Y/N) =>"); 
            String response = scan.nextLine().trim().toLowerCase();;
            switch (response) {
                case "y":
                    readUserInput("(Y) Salvar");
                    try {
                        if(user.isStatus()){
                            user.setStatus(false);
                            userService.updateUser(user);
                            System.out.println("Usuario desativado com sucesso");
                        }else{
                            user.setStatus(true);
                            userService.updateUser(user);
                            System.out.println("Usuario ativado com sucesso");   
                        }
                        listUsers();
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao alterar usuário: " + e.getMessage());
                    }
                    break;
                case "n":
                    readUserInput("(N) Cancelar");
                    System.out.println("Operação cancelada.");
                    listUsers();
                    break;
                default:
                    System.out.println("Opção invalida. Tente Novamente.");
                    break;
            }
        }

    }

    //Tela para cadastrar novo usuário
    public void includeUser(){
        String name, cpf, email, grupo, password, response;
        System.out.println("                                    Incluir usuário                                        ");
        name = userValidator.nameValidate();
        cpf = userValidator.cpfValidator();
        email = userValidator.emailValidator();
        grupo = userValidator.validateGroup();
        password = userValidator.passAble("Senha => ");
        System.out.println(generateLineSeparator(140));
        User newUser = new User(name, cpf, email, password, grupo,true);
        while (true) {
            System.out.print("Salvar (Y/N) => ");
            response = scan.nextLine().trim().toLowerCase();;
            switch (response) {
                case "y":
                readUserInput("(Y) Salvar");
                try {
                    String result = userService.registerUser(newUser);
                    System.out.println(result);
                    listUsers();
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro ao registrar usuário: " + e.getMessage());
                }
                    break;    
                case "n":
                    readUserInput("(N) Cancelar");
                    System.out.println("Operação cancelada.");
                    listUsers();
                    break;
                default:
                    System.out.println("Opção invalida. Tente Novamente.");
                    break;
            }    
        }
        
    }

    //Metodo para criação de linhas
    public String generateLineSeparator(int length) {
        return "-".repeat(Math.max(0, length));
    }

    //Método que imprime as opções do usuário
    public void readUserInput(String option){
        System.out.println("Você escolheu a opção: "+option);    
    }


}
