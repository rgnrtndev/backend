package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE username=?1 and is_deleted=false", nativeQuery = true)
    Optional<User> findByUsernameAndIsDeletedFalse(String username);

    @Query(nativeQuery = true, value = "SELECT username FROM users where id =?1")
    String findUsernameById(Long id);
}
