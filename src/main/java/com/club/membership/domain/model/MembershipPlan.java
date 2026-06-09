package com.club.membership.domain.model;

import com.club.membership.domain.enums.BillingCycle;
import java.math.BigDecimal;

public record MembershipPlan(Long id, String name, BillingCycle billingCycle, BigDecimal price) {}
