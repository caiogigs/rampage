package br.com.pi.rampage.service;

//Classe Global para criação da sessão
public class UserSession {
    
    //Atributos
    private static String email;
    private static String userGroup;

    

    //Método que cria a Sessão
    public static void setUserSession(String userEmail, String group) {
        email = userEmail;
        userGroup = group;
    }

    //Método que limpa a sessão
    public static void clearSession() {
        email = null;
        userGroup = null;
    }

   //Métodos Acessores
    public static String getEmail() {
        return email;
    }

   
    public static String getUserGroup() {
        return userGroup;
    }
}
    
