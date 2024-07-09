package com.example.planManagement.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Node("Customer")
public class Customer {

    @Id
    @Property("customerId")
    private String id;

    @Property("name")
    private String name;

    @Property("phone")
    private String phoneNumber;

    @Property("email")
    private String email;

    @JsonManagedReference
    @Relationship(type = "HAS_PLAN", direction = Relationship.Direction.OUTGOING)
    private List<RouterPlan> routerPlans;

    @JsonManagedReference
    @Relationship(type = "HAS_SIM_CARD", direction = Relationship.Direction.OUTGOING)
    private List<SimCard> simCards;

}
