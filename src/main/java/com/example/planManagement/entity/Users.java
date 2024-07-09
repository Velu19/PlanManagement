package com.example.planManagement.entity;


import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Node("User")
public class Users {

    @Id
    @Property("userId")
    private Long id;

    @Property("name")
    @NonNull
    private String name;

    @Property("phone")
    @NonNull
    private String phoneNumber;

    @Property("email")
    @NonNull
    private String email;

    @Property("password")
    @NonNull
    private String password;



}
