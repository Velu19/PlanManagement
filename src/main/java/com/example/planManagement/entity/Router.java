package com.example.planManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Node("Router")
public class Router {

    @Property("serialNumber")
    @Id
    private String serialNumber;

    @Property("routerId")
    private String routerId;

    @Property("model")
    private String model;

    @Property("firmwareVersion")
    private String firmwareVersion;

}
