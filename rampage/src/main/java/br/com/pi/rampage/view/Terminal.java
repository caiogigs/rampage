package br.com.pi.rampage.view;


import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pi.rampage.controle.Controle;
import br.com.pi.rampage.model.User;

@Component
public class Terminal {
    
    Scanner scan = new Scanner(System.in);
    InputFilter inputFilter = new InputFilter(); 

    @Autowired
    private Controle controle;


    public void includeUser(){
        String name, cpf, email, grupo, senha, resposta;
        System.out.println("                              Incluir usuário                                        ");
        name = inputFilter.validaNome();
        cpf = inputFilter.cpfValidator();
        email = inputFilter.emailValidator();
        grupo = inputFilter.validarGrupo();
        senha = inputFilter.validaSenha();
        System.out.println("-------------------------------------------------------------------------------------");
        
        while (true) {
            System.out.print("Salvar (Y/N) => ");
            resposta = scan.nextLine().trim();
            switch (resposta) {
                case "Y":
                    User newUser = new User(name,cpf,email,senha,grupo,true);
                    try {
                        controle.registrar(newUser);
                        System.out.println("Novo usuário cadastrado com sucesso!");
                        return;
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
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
