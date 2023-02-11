package org.vaadin.example.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.vaadin.example.data.entity.User;

public interface UserRepository extends CrudRepository<User, String>{
    
}
