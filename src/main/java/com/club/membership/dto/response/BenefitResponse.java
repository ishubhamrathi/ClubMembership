package com.club.membership.dto.response;

import com.club.membership.domain.enums.BenefitType;

import java.util.Map;

public record BenefitResponse (
        BenefitType benefitType,
        Map<String, Object> configuration
) {
}
