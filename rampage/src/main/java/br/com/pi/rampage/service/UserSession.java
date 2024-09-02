package br.com.pi.rampage.service;

public class UserSession {
    
    private static String email;
    private static String userGroup;

    

    // Método para definir o email e o grupo do usuário
    public static void setUserSession(String userEmail, String group) {
        email = userEmail;
        userGroup = group;
    }

    // Método para limpar a sessão do usuário
    public static void clearSession() {
        email = null;
        userGroup = null;
    }

    // Método para obter o email do usuário
    public static String getEmail() {
        return email;
    }

    // Método para obter o grupo do usuário
    public static String getUserGroup() {
        return userGroup;
    }
}
    
