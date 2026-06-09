package com.club.membership.strategy.impl;

import com.club.membership.domain.enums.RuleType;
import com.club.membership.domain.model.TierRule;
import com.club.membership.dto.request.TierEvaluationRequest;
import com.club.membership.strategy.TierEvaluationStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TotalOrderValueStrategy implements TierEvaluationStrategy {

    @Override
    public boolean supports(RuleType ruleType) {

        return RuleType.TOTAL_ORDER_VALUE.equals(ruleType);
    }

    @Override
    public boolean isEligible(
            TierRule rule,
            TierEvaluationRequest request
    ) {

        return request.totalOrderValue()
                .compareTo(
                        new BigDecimal(rule.threshold())
                ) >= 0;
    }
}