package com.example.planManagement.repository;

import com.example.planManagement.dto.SimCardWithPlans;
import com.example.planManagement.entity.Customer;
import com.example.planManagement.entity.Router;
import com.example.planManagement.entity.RouterPlan;
import com.example.planManagement.entity.SimCard;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface Customer_Repository extends Neo4jRepository<Customer,String> {

    @Query("MATCH (customer:Customer) RETURN customer")
    List<Customer> findAllCustomersWithLabels();

    Optional<Customer> findByEmail(String email);

    @Query("MATCH (c:`Customer`) WHERE c.phone = $phoneNumber RETURN c")
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    @Query("MATCH (c:`Customer`) WHERE c.name = $name RETURN c")
    Optional<Customer> findByName(String name);

    @Query("Match(n:Customer) WHERE n.phone = $email or n.email = $email RETURN n")
    Optional<Customer> findByPhoneNumberOrEmail(String email);

    @Query("Match(n:Customer)-[:HAS_PLAN]->(p:Plan) WHERE n.customerId = $customerId " +
            "RETURN p.duration as duration, p.uniqueID as uniqueID, p.planId as planId," +
            "p.serviceTrue as serviceTrue, p.serviceType as serviceType, p.price as price," +
            "p.name as name, p.speed as speed,p.startDate as startDate")
    List<RouterPlan> findRouterPlans(String customerId);

    @Query("Match (n:Customer)-[:HAS_SIM_CARD]->(s:SimCard) WHERE n.customerId = $customerId " +
            "RETURN s.iccid as iccid, s.simType as simType," +
            "s.simCardId as simCardId, s.networkOperator as networkOperator,s.activationDate as activationDate," +
            "s.phoneNumber as phone")
    List<SimCard> findSimCards(String customerId);


    @Query("MATCH(n:Customer)-[:HAS_SIM_CARD]->(s:SimCard) " +
            "MATCH(s)-[:HAS_MOBILE_PLAN]->(m:MobilePlan) " +
            "MATCH(m)-[:GENERATES_DATA_USAGE]->(d:DataUsage) " +
            "WHERE n.customerId = $customerId " +
            "return s.simType as simType, s.phoneNumber as phone, s.networkOperator as networkOperator, s.simCardId as simCardId, s.activationDate as activationDate, " +
            "m.duration as duration, m.voiceCallDetails as voiceCallDetails, m.price as price, " +
            "m.planName as planName, m.dataAllowance as dataAllowance, m.planType as planType, m.serviceTrue as serviceTrue, m.startDate as startDate, " +
            "d.dataLimit as dataLimit, d.dataUsed as dataUsed"
            )
    List<SimCardWithPlans> findSimWithPlans(String customerId);


    @Query("MATCH(n:Plan)\n" +
            "where n.uniqueID = 14\n" +
            "MATCH(n)-[:USES_ROUTER]->(r:Router)\n" +
            "return r.serialNumber as serialNumber,r.routerId as routerId, r.model as model,r.firmwareVersion as firmwareVersion")
    Router getRouterForPlan(Integer uniqueID);



}
