package com.example.planManagement.repository;

import com.example.planManagement.entity.RouterPlan;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RouterPlanRepository extends Neo4jRepository<RouterPlan , Integer> {
}
