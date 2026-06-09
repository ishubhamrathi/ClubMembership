package com.club.membership.mapper;

import com.club.membership.domain.model.MembershipPlan;
import com.club.membership.dto.response.MembershipPlanResponse;
import org.springframework.stereotype.Component;

@Component
public class MembershipPlanResponseMapper {

    public MembershipPlanResponse toResponse(MembershipPlan membershipPlan) {

        return new MembershipPlanResponse(
                membershipPlan.id(),
                membershipPlan.name(),
                membershipPlan.billingCycle(),
                membershipPlan.price());
    }
}
