package com.example.basicAuth.repository;

import com.example.basicAuth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

}
