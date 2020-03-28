package com.kaan.eshop.authservice.backend.repository;

import com.kaan.eshop.authservice.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Kaan Kocabas
 */
public interface UserRepository extends MongoRepository<User, String> {

    public Optional<User> findByEmail(String email);

    public User findOneByEmailIgnoreCase(String email);

//	public List<User> findByRoles(Set<Role> roles);

}
