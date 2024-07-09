package com.example.planManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Node("Plan")
public class RouterPlan {
    @Property("uniqueID")
    @Id
    private Integer uniqueID;

    @Property("planId")
    private String planId;

    @Property("serviceTrue")
    private boolean serviceTrue;

    @Property("serviceType")
    private String serviceType;

    @Property("duration")
    private Integer duration;

    @Property("price")
    private Integer price;

    @Property("name")
    private String name;

    @Property("speed")
    private String speed;

    @Property("startDate")
    private String startDate;

    @JsonBackReference
    @Relationship(type = "HAS_PLAN", direction = Relationship.Direction.INCOMING)
    private Customer customer;

}
