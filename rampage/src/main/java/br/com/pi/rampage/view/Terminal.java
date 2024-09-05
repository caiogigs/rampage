package br.com.pi.rampage.view;


import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

//Tela principal do BackOffice
    public void homeBackOffice(){
        String response;
        printCentered("HomePage BackOffice", 100);
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
                    homeBackOffice();
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
                    printSlowly("Saindo.....", 100);
                    start();;
                    break;
                default:
                    printSlowly("Entrada inválida. Por favor, insira uma das opções listadas.", 100);
                    homeBackOffice();
                    break;
            }    
        }
        

    }

    //Método para listar produtos
    public void listProducts(){
        printSlowly("Não ha produtos para listar", 100);       
    }

    //Tela com lista de usuários e opções
    public void listUsers() {
        String response;
        printCentered("Listar Usuários", 100);
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
                    printSlowly("Entrada inválida. Por favor, insira uma das opções listadas.", 100);
                    listUsers(); 
                }
                break;
        }
    }

    //Tela de edição do usuário
    public void editUser(User user){
        printCentered("Opção de edição de usuário", 100);
        System.out.println(user);
        System.out.println(generateLineSeparator(120));
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
                    printSlowly("Entrada inválida. Por favor, insira uma das opções listadas.", 100);
                    editUser(user);;
                    break;
            }    
        }
       
    }

    //Tela para atualizar a senha do usuario
    public void updatePassword(User user){
        printCentered("Alterar senha", 100);
        System.out.println(user);
        System.out.println(generateLineSeparator(120));
        System.out.println("\n");
        System.out.print("Nova Senha =>");
        String pass = scan.nextLine().trim();
        if (pass.isEmpty()) {
            printSlowly("Senha não pode ficar em branco. Tente novamente.", 100);
            updatePassword(user);
        }
        boolean correct = userValidator.corretPass(pass);
        if(correct){
            while (true) {
                System.out.print("Salvar (Y/N) => ");
                String response = scan.nextLine().trim().toLowerCase();
                switch (response) {
                    case "y":
                        try{
                            user.setSenha(passwordEncoder.encode(pass));
                            readUserInput("(Y) Salvar");
                            userService.updateUser(user);
                            printSlowly("Senha alterada com sucesso!", 100);
                            listUsers();   
                            break; 
                        }catch(IllegalArgumentException e) {
                            System.out.println("Erro ao altarar senha do usuário: " + e.getMessage());
                        }
                        
                    case "n":
                        readUserInput("(N) Cancelar");
                        printSlowly("Operação cancelada.", 100);    
                        listUsers();
                        break;
                    default:
                        printSlowly("Entrada inválida. Por favor, insira uma das opções listadas.", 100);
                        break;
                    }     
            }
            
        }else{
            printSlowly("As senhas não conferem. Tente novamente.", 100);
            updatePassword(user);        
        }
    }


    //Tela para atualizar informações do usuário
    public void updateUser(User user){
        printCentered("Alterar usuário", 100);;
        System.out.println(user);
        System.out.println(generateLineSeparator(120));
        user.setNome(userValidator.nameValidate());
        user.setCpf(userValidator.cpfValidator());
        user.setGrupo(userValidator.validateGroup());
        while (true) {
            System.out.print("Salvar (Y/N) => ");
            String response = scan.nextLine().trim().toLowerCase();;
            switch (response) {
                case "y":
                try {
                    userService.updateUser(user);
                    readUserInput("(Y) Salvar");
                    printSlowly("Usuario alterado com sucesso!", 100);
                    listUsers();
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro ao alterar usuário: " + e.getMessage());
                }
                    break;
                case "n":
                    readUserInput("(N) Cancelar");
                    printSlowly("Operação cancelada.", 100);
                    listUsers();
                    break;
                default:
                    printSlowly("Entrada inválida. Por favor, insira uma das opções listadas.", 100);
                    break;
            }       
        }
    }

    //Tela de login
    public boolean enter() {
        String email, password;
        LoginStatus loginStatus;
        while (true) {
            printCentered("Login", 100);  
            System.out.println("\n" + "\n");
            System.out.print("Email: ");
            email = scan.nextLine().trim();
            System.out.print("Senha: ");
            password = scan.nextLine().trim();
    
            loginStatus = userService.login(email, password);
    
            switch (loginStatus) {
                case SUCCESS:
                    printSlowly("Login bem-sucedido!", 100);
                    homeBackOffice();
                    return true;
                case USER_INACTIVE:
                    printSlowly("Login falhou. Usuário inativo.", 100);
                    break;
                case CLIENT_ACCESS_DENIED:
                    printSlowly("Acesso Negado. Clientes não têm permissão para acessar esta área.", 100);
                    break;
                default:
                    printSlowly("Login falhou. Email ou senha incorretos.", 100);
                    break;
            }
            
            while (true) {
                System.out.print("Deseja tentar efetuar login novamente? (Y/N): ");
                String tryAgain = scan.nextLine().trim().toLowerCase();
                switch (tryAgain) {
                    case "y":
                        readUserInput("(Y) Tentar Novamente");
                        enter();
                        break;
                    case "n":
                        readUserInput("(N) Cancelar");
                        start();
                        break;
                    default:
                        printSlowly("Entrada inválida. Por favor, insira uma das opções listadas.", 100);
                        break;
                }    
            }
        }
    }
    
    //Tela inicial do sistema
    public void start() {
        while (true) {
            printCentered("Bem-vindo ao Back-Office RampageStore!", 100);
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
                    printSlowly("Encerrando o sistema...", 100);
                    System.exit(0); 
                    break;
                default:
                    printSlowly("Entrada inválida. Por favor, insira uma das opções listadas.", 100);
                    start();
                    break;
            }
        }
    }

    //Tela para ativar/desativar usuarios
    public void activateUser(User user){
        printCentered("Ativar/Desativar usuário", 100);
        System.out.println(user);
        System.out.println(generateLineSeparator(120));
        System.out.println("\n");
        if(user.isStatus()){
            System.out.println("\n"+"<<Desativar usuário>>");
        }else{
            System.out.println("\n"+"<<Ativar usuário>>");
        }
        while (true) { 
            System.out.print("\n"+"Salvar alteração (Y/N) =>"); 
            String response = scan.nextLine().trim().toLowerCase();
            switch (response) {
                case "y":
                    readUserInput("(Y) Salvar");
                    try {
                        if(user.isStatus()){
                            user.setStatus(false);
                            userService.updateUser(user);
                            printSlowly("Usuario desativado com sucesso", 100);
                        }else{
                            user.setStatus(true);
                            userService.updateUser(user);
                            printSlowly("Usuario ativado com sucesso", 100);
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
                    printSlowly("Entrada inválida. Por favor, insira uma das opções listadas.", 100);
                    break;
            }
        }

    }

    //Tela para cadastrar novo usuário
    public void includeUser(){
        String name, cpf, email, grupo, password, response;
        printCentered("Incluir usuário", 100);
        System.out.println(generateLineSeparator(120));
        name = userValidator.nameValidate();
        cpf = userValidator.cpfValidator();
        email = userValidator.emailValidator();
        grupo = userValidator.validateGroup();
        password = passwordEncoder.encode(firtPassword());
        System.out.println(generateLineSeparator(120));
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
                    printSlowly("Operação cancelada.", 100);
                    listUsers();
                    break;
                default:
                    printSlowly("Entrada inválida. Por favor, insira uma das opções listadas.", 100);
                    System.out.println();
                    break;
            }    
        }
        
    }

    //centraliza texto
    public void printCentered(String text, int width) {
        int padding = (width - text.length()) / 2;
        String paddedText = " ".repeat(Math.max(0, padding)) + text;
        System.out.println(paddedText);
    }


    //Metodo para criação de linhas
    public String generateLineSeparator(int length) {
        return "-".repeat(Math.max(0, length));
    }

    //Método que imprime as opções do usuário
    public void readUserInput(String option){
        printSlowly("Você escolheu a opção: "+option, 100);   
    }

    public String firtPassword() {
        System.out.print("Senha => ");
        String pass = scan.nextLine().trim();
        if (pass.isEmpty()) {
            printSlowly("Senha não pode ficar em branco. Tente novamente.", 100);
            return firtPassword();  
        }
        boolean correct = userValidator.corretPass(pass);
        if (correct) {
            return pass;
        } else {
            printSlowly("As senhas não coincidem. Tente novamente.",100);
            return firtPassword(); 
        }
    }

    //Imprimir devagar
    public void printSlowly(String text, int delay) {
        for (char ch : text.toCharArray()) {
            System.out.print(ch);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
            }
        }
        System.out.println(); 
    }

}
