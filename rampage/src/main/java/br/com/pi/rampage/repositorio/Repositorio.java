package br.com.pi.rampage.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pi.rampage.model.User;


@Repository
public interface Repositorio extends CrudRepository<User, Integer> {
    
}
