package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.enums.Status;
import com.rcc.dev.backend.model.UserMDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserMGDRepository extends MongoRepository<UserMDB, String> {
    List<UserMDB> findAllByStatus(Status status);
}
