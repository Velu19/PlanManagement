package com.example.planManagement.repository;

import com.example.planManagement.entity.Users;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<Users,Long> {

    Optional<Users> findByPhoneNumber(String phoneNumber);

    Optional<Users> findByEmail(String email);

    @Query("Match(n:User) WHERE n.phone = $email or n.email = $email RETURN n")
    Optional<Users> findByPhoneNumberOrEmail(String email);
}
