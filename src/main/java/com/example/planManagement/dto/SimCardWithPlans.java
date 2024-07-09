package com.example.planManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimCardWithPlans {

    private String simType;

    private String phoneNumber;

    private String networkOperator;

    private String simCardId;

    private String activationDate;

    private Integer duration;

    private String PlanType;

    private String voiceCallDetails;

    private Integer price;

    private String planName;

    private String dataAllowance;

    private String planId;

    private boolean serviceTrue;

    private String startDate;

}
