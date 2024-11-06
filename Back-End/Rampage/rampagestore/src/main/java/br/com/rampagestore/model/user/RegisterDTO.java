package br.com.rampagestore.model.user;

import java.time.LocalDate;

public record RegisterDTO(String name, LocalDate birthDate, String gender, String cpf, String email, String password, UserRole role) {
    
}
