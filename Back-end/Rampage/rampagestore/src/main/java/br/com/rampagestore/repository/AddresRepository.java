package br.com.rampagestore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rampagestore.model.user.UserAddress;

@Repository
public interface AddresRepository  extends CrudRepository<UserAddress, Long> {
    
    List<UserAddress> findAllByIdUser(long idUser);

    UserAddress findById(long id);
}
