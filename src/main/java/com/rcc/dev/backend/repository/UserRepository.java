package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE username=?1 and is_deleted=false", nativeQuery = true)
    Optional<User> findByUsernameAndIsDeletedFalse(String username);

    @Query(nativeQuery = true, value = "SELECT username FROM users where id =?1")
    String findUsernameById(Long id);

    @Query(value = "SELECT s FROM users s WHERE s.email=?1", nativeQuery = true)
    Optional<User> findUserByEmail(String email);

    @Query(value = "SELECT * FROM users where is_deleted = false", nativeQuery = true)
    List<User> findAllUserWhereIsDeletedFalse();
}
