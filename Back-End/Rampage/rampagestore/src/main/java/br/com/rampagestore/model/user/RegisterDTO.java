package br.com.rampagestore.model.user;

public record RegisterDTO(String name, String cpf, String email, String password, UserRole role) {
    
}
