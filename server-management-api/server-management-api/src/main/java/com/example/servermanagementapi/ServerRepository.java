package com.example.servermanagementapi;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ServerRepository extends MongoRepository<Server, String> {
    Optional<Server> findById(String id);
    List<Server> findByNameContaining(String name);
}
