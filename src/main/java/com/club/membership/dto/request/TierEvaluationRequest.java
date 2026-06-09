package com.club.membership.dto.request;

import java.math.BigDecimal;

public record TierEvaluationRequest(
        Integer orderCount,
        BigDecimal totalOrderValue,
        String cohort
) {
}