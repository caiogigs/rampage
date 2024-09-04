package br.com.pi.rampage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pi.rampage.model.User;

/*/Interface Repositorio herda métodos para realizar operações básicas de CRUD. */ 
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
 
    //Busca usuário no banco de dados pelo email
    Optional<User> findByEmail(String email);

    //Lista todos o usuários registrados no banco de dados
    @SuppressWarnings("null")
    List<User> findAll();

    //Busca usuário no banco de dados pelo codigo 
    Optional<User> findByCodigo(int codigo);
    
}
