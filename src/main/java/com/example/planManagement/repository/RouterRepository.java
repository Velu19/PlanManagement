package com.example.planManagement.repository;


import com.example.planManagement.entity.Router;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RouterRepository extends Neo4jRepository<Router,String> {
}
