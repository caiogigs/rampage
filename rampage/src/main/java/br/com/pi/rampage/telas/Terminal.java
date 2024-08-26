package br.com.pi.rampage.telas;

import java.util.Scanner;

public class Terminal {
    
    Scanner scan = new Scanner(System.in);
    InputFilter inputFilter = new InputFilter(); 


    public void includeUser(){
        String name, cpf, email, grupo, senha;
        System.out.println("                              Incluir usuário                                        ");
        name = inputFilter.validaNome();
        cpf = inputFilter.cpfValidator();
        email = inputFilter.emailValidator();
        grupo = inputFilter.validarGrupo();
        senha = inputFilter.validaSenha();
        System.out.println("-------------------------------------------------------------------------------------");
    }


}
