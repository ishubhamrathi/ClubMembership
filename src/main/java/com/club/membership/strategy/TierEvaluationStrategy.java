package com.club.membership.strategy;

import com.club.membership.domain.enums.RuleType;
import com.club.membership.domain.model.TierRule;
import com.club.membership.dto.request.TierEvaluationRequest;

public interface TierEvaluationStrategy {

    boolean supports(RuleType ruleType);

    boolean isEligible(
            TierRule rule,
            TierEvaluationRequest request
    );
}
