package br.com.rampagestore.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.rampagestore.model.ImageModel;

public interface ImageRepository extends CrudRepository<ImageModel, Long>{
    
}
