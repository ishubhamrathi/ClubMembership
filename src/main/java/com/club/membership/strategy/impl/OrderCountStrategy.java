package com.club.membership.strategy.impl;

import com.club.membership.domain.enums.RuleType;
import com.club.membership.domain.model.TierRule;
import com.club.membership.dto.request.TierEvaluationRequest;
import com.club.membership.strategy.TierEvaluationStrategy;
import org.springframework.stereotype.Component;

@Component
public class OrderCountStrategy implements TierEvaluationStrategy {

    @Override
    public boolean supports(RuleType ruleType) {

        return RuleType.ORDER_COUNT.equals(ruleType);
    }

    @Override
    public boolean isEligible(
            TierRule rule,
            TierEvaluationRequest request
    ) {

        return request.orderCount() >= Integer.parseInt(rule.threshold());
    }
}
