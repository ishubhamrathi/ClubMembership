package com.club.membership.dto.response;

import com.club.membership.domain.enums.BillingCycle;

import java.math.BigDecimal;

public record MembershipPlanResponse (
        Long id,
        String name,
        BillingCycle billingCycle,
        BigDecimal price
) {

}
