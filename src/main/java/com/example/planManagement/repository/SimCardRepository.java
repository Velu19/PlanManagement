package com.example.planManagement.repository;

import com.example.planManagement.entity.SimCard;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SimCardRepository extends Neo4jRepository<SimCard,String> {

}
