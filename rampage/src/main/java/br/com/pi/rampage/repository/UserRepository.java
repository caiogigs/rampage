package br.com.pi.rampage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pi.rampage.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
 
    Optional<User> findByEmail(String email);


    @SuppressWarnings("null")
    List<User> findAll();


    Optional<User> findByCodigo(int codigo);
    
}
