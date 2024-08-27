package br.com.pi.rampage.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputFilter {
    
    Scanner scan = new Scanner(System.in);

    //metodo para validar o nome
    public String validaNome(){
        String nome;
        while (true) {
            System.out.print("Nome => ");
            nome = scan.nextLine().trim();
            if(nome.matches("[a-zA-Z\\s]+")){
                return nome;
            }else{
                System.out.println("Nome inválido. Tente novamente.");    
            }
        }
    }

    //metodo para validar cpf
    public String cpfValidator(){
        String cpf;
        while (true) {
            System.out.print("CPF => ");
            cpf = scan.nextLine().trim();
            cpf = cpf.replaceAll("[^0-9]", ""); //<= remove caracteres não numericos do cpf
            if (cpfValido(cpf)) {
                return formatarCPF(cpf); // <=Retorna o CPF formatado se for válido
            } else {
                System.out.println("CPF inválido. Tente novamente.");
            }
        
        }
    }

    //metodo de validação do email.
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

    public String validarGrupo(){
        String grupo;
        while (true) {
            System.out.print("Grupo (Administrador/Estoquista) => ");
            grupo = scan.nextLine().trim();
            if(grupo.equals("Administrador")){
                return "Administrador";
            }else if(grupo.equals("Estoquista")){
                return "Estoquista";
            }else{
                System.out.println("Grupo inválido. Tente novamente.");
            }
        }
    }

    //metodo para verificação da senha
    public String validaSenha() {
        String senha1, senha2;
        while (true) {
            System.out.print("Senha => ");
            senha1 = scan.nextLine().trim(); 
            if (!senha1.isEmpty()) {
                break;
            } else {
                System.out.println("Senha não pode ficar em branco. Tente novamente.");
            }
        }
        while (true) {
            System.out.print("Repetir senha => ");
            senha2 = scan.nextLine().trim(); 
            if (!senha2.isEmpty() && senha2.equals(senha1)) {
                break;
            } else if (senha2.isEmpty()) {
                System.out.println("Senha não pode ficar em branco. Tente novamente.");
            } else {
                System.out.println("As senhas não conferem. Tente novamente.");
            }
        }
        return senha1;
    }
    

    //Metodos extras para validação do cpf
    private boolean cpfValido(String cpf) {
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
        int primeiroDigito = (total * 10) % 11;
        if (primeiroDigito == 10) primeiroDigito = 0;
        if (primeiroDigito != (cpf.charAt(9) - '0')) {
            return false;
        }

        // Calcula e valida o segundo dígito verificador
        total = 0;
        for (int i = 0; i < 10; i++) {
            total += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = (total * 10) % 11;
        if (segundoDigito == 10) segundoDigito = 0;
        return segundoDigito == (cpf.charAt(10) - '0');
    }

    // Formata o CPF para o formato padrão (xxx.xxx.xxx-xx)
    private String formatarCPF(String cpf) {
        return String.format("%s.%s.%s-%s",
                cpf.substring(0, 3),
                cpf.substring(3, 6),
                cpf.substring(6, 9),
                cpf.substring(9, 11));
    }


    

}
