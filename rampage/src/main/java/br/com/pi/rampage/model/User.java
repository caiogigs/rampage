package br.com.pi.rampage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*Classe user Reponsavel por reprensetar os dados do usuário
 * e mapeamento para Banco de Dados:
 */


@Entity //Annotation usada pelo Spring para criar a tabela users no banco de dados
@Table(name = "users")//Annotation que define o nome da Tabela
public class User {


    //Atributos do Usuário
    @Id//Annotation para gerar o atributo identificador no banco de dados 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String grupo;
    private boolean status;

    //Construtores
    public User(){

    }

    public User(String nome, String cpf, String email, String senha, String grupo, boolean status) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.grupo = grupo;
        this.status = status;
    }

    //Metodos Acessores 
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    @Override//Transforma a instancia de usuário em uma String com todos seus atributos 
    public String toString() {
        String whichStatus = isStatus() ? "Ativo" : "Inativo";
        return String.format("Id: "+codigo+"\n"
        +"Nome: "+nome+"\n"
        +"E-mail: "+email+"\n"
        +"Status: "+whichStatus+"\n"
        +"Grupo: "+grupo);
    }
    
}
