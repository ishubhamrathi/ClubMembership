package com.club.membership.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TierEvaluationRequest(
        @NotNull
        @Min(0)
        Integer orderCount,

        @NotNull
        BigDecimal totalOrderValue,

        String cohort
) {
}