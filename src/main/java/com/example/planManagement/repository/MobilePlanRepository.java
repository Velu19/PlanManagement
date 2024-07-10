package com.example.planManagement.repository;

import com.example.planManagement.entity.MobilePlan;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MobilePlanRepository extends Neo4jRepository<MobilePlan,Integer> {
}
