package br.com.pi.rampage.model;

public class User {

    //Atributos do Usuario
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String grupo;
    private boolean status;

    //Construtores
    public User(){

    }

    public User(String nome,String cpf,String email,String senha,String grupo){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.grupo = grupo;
    }

    //Metodos Acessores 
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
    
    

}
