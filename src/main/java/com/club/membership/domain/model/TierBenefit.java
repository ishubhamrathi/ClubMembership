package com.club.membership.domain.model;

import com.club.membership.domain.enums.BenefitType;
import com.club.membership.domain.enums.TierType;
import lombok.Builder;

@Builder
public record TierBenefit(
        Long id,
        TierType tierType,
        BenefitType benefitType,
        String configuration
) {
}
