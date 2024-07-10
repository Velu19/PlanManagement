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
import org.springframework.web.bind.annotation.PostMapping;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Node("MobilePlan")
public class MobilePlan {

    @Id
    @Property("uniqueID")
    private Integer uniqueID;

    @Property("duration")
    private Integer duration;

    @Property("planType")
    private String planType;

    @Property("voiceCallDetails")
    private String voiceCallDetails;

    @Property("price")
    private Integer price;

    @Property("planName")
    private String planName;

    @Property("dataAllowance")
    private String dataAllowance;

    @Property("planId")
    private String planId;

    @Property("serviceTrue")
    private boolean serviceTrue;

    @Property("startDate")
    private String startDate;

    @JsonBackReference
    @Relationship(type = "HAS_MOBILE_PLAN",direction = Relationship.Direction.INCOMING)
    private SimCard simCard;


}
