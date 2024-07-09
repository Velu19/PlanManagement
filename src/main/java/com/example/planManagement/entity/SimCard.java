package com.example.planManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Node("SimCard")
public class SimCard {

    @Id
    @Property("iccid")
    private String iccid;

    @Property("simType")
    private String simType;

    @Property("phoneNumber")
    private String phoneNumber;

    @Property("simCardId")
    private String simCardId;

    @Property("networkOperator")
    private String networkOperator;

    @Property("activationDate")
    private String activationDate;

    @JsonBackReference
    @Relationship(type = "HAS_SIM_CARD",direction = Relationship.Direction.INCOMING)
    private Customer customer;

    @JsonManagedReference
    @Relationship(type = "HAS_MOBILE_PLAN",direction = Relationship.Direction.OUTGOING)
    private MobilePlan mobilePlan;


}
