package com.club.membership.service.impl;

import com.club.membership.context.UserContext;
import com.club.membership.dao.TierRuleDao;
import com.club.membership.domain.enums.TierType;
import com.club.membership.domain.model.TierRule;
import com.club.membership.dto.request.TierEvaluationRequest;
import com.club.membership.service.TierEvaluationService;
import com.club.membership.strategy.TierEvaluationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TierEvaluationServiceImpl implements TierEvaluationService {

    private final TierRuleDao tierRuleDao;
    private final List<TierEvaluationStrategy> strategies;

    @Override
    public TierType evaluateTier(
            TierEvaluationRequest request,
            UserContext userContext
    ) {

        for (TierType tierType : TierType.values()) {

            List<TierRule> rules =
                    tierRuleDao.getByTier(
                            tierType,
                            userContext
                    );

            boolean eligible = rules.stream()
                    .allMatch(rule ->
                            strategies.stream()
                                    .filter(s ->
                                            s.supports(
                                                    rule.ruleType()))
                                    .findFirst()
                                    .orElseThrow()
                                    .isEligible(rule, request));

            if (eligible) {
                return tierType;
            }
        }

        return TierType.SILVER;
    }
}
