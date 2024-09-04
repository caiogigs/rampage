package br.com.pi.rampage.service.validation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/*Classe Responsavel em validar os inputs do usuário*/

/* Annotation Component usada para marcar uma classe como um componente 
gerenciado pelo Spring*/
@Component
public class UserValidator {
    Scanner scan = new Scanner(System.in);
    
    @Autowired//Injeção Automatica de Dependâncias 
    private PasswordEncoder passwordEncoder;


    //Método de validação do nome informado pelo usuário 
    public String nameValidate(){
        String name;
        while (true) {
            System.out.print("Nome => ");
            name = scan.nextLine().trim();
            if(name.matches("[a-zA-Z\\s]+")){
                return name;
            }else{
                System.out.println("Nome inválido. Tente novamente.");    
            }
        }
    }

    //Método para validar o CPF informado pelo usuário 
    public String cpfValidator(){
        String cpf;
        while (true) {
            System.out.print("CPF => ");
            cpf = scan.nextLine().trim();
            cpf = cpf.replaceAll("[^0-9]", ""); //<= remove caracteres não numericos do cpf
            if (validCpf(cpf)) {
                return formatCpf(cpf); // <=Retorna o CPF formatado se for válido
            } else {
                System.out.println("CPF inválido. Tente novamente.");
            }
        
        }
    }

    //Método para validar o email informado pelo usuário
    public String emailValidator(){
        String email;
        String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regexEmail);

        while (true) {
            System.out.print("E-mail => ");
            email = scan.nextLine().trim();
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return email; 
            } else {
                System.out.println("E-mail inválido. Tente novamente.");
            }
        }
    }

    //Método para validar o grupo email informado pelo usuário
    public String validateGroup(){
        String group;
        while (true) {
            System.out.print("Grupo (Administrador/Estoquista) => ");
            group = scan.nextLine().trim();
            if("Administrador".equalsIgnoreCase(group)){
                return "Administrador";
            }else if("Estoquista".equalsIgnoreCase(group)){
                return "Estoquista";
            }else{
                System.out.println("Grupo inválido. Tente novamente.");
            }
        }
    }

    //Método para validar as senhas informadas pelo usuário
    public String passAble(String msg) {
        String password1, password2;
        while (true) {
            System.out.print(msg);
            password1 = scan.nextLine().trim(); 
            if (!password1.isEmpty()) {
                break;
            } else {
                System.out.println("Senha não pode ficar em branco. Tente novamente.");
            }
        }
        while (true) {
            System.out.print("Repetir senha => ");
            password2 = scan.nextLine().trim(); 
            if (!password2.isEmpty() && password2.equals(password1)) {
                break;
            } else if (password2.isEmpty()) {
                System.out.println("Senha não pode ficar em branco. Tente novamente.");
            } else {
                System.out.println("As senhas não conferem. Tente novamente.");
            }
        }
        return passwordEncoder.encode(password1); 
    }

    /*Método para validar o input do usuário na tela Listar Usúarios é numerico
    Auxilia na compreensão se o usuário deseja informar o ID de um usuário existente
    para realizar alterações*/
    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }





    //Metodos complementares  para validação do cpf

    
    private boolean validCpf(String cpf) {
        if (cpf.length() != 11) { //<= Verifica se o CPF tem 11 dígitos
            return false;
        }

        
        if (cpf.chars().allMatch(c -> c == cpf.charAt(0))) { //<= Verifica se todos os dígitos são iguais (ex: 111.111.111-11)
            return false;
        }

        // Calcula e valida o primeiro dígito verificador
        int total = 0;
        for (int i = 0; i < 9; i++) {
            total += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstDigit = (total * 10) % 11;
        if (firstDigit == 10) firstDigit = 0;
        if (firstDigit != (cpf.charAt(9) - '0')) {
            return false;
        }

        // Calcula e valida o segundo dígito verificador
        total = 0;
        for (int i = 0; i < 10; i++) {
            total += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondDigit = (total * 10) % 11;
        if (secondDigit == 10) secondDigit = 0;
        return secondDigit == (cpf.charAt(10) - '0');
    }

    //Método para Formatar o CPF para o formato padrão (xxx.xxx.xxx-xx)
    private String formatCpf(String cpf) {
        return String.format("%s.%s.%s-%s",
                cpf.substring(0, 3),
                cpf.substring(3, 6),
                cpf.substring(6, 9),
                cpf.substring(9, 11));
    }

}
