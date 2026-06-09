package com.club.membership.domain.model;

import com.club.membership.domain.enums.RuleType;
import com.club.membership.domain.enums.TierType;

public record TierRule(
        Long id,
        TierType tierType,
        RuleType ruleType,
        String threshold
) {
}
