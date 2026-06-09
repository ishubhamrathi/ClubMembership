package com.club.membership.domain.model;

import com.club.membership.domain.enums.BenefitType;
import com.club.membership.domain.enums.TierType;

public record TierBenefit(
        Long id, TierType tierType, BenefitType benefitType, String configuration) {}
