package com.club.membership.service.impl;

import com.club.membership.context.UserContext;
import com.club.membership.dao.TierRuleDao;
import com.club.membership.domain.enums.TierType;
import com.club.membership.domain.model.TierRule;
import com.club.membership.dto.request.TierEvaluationRequest;
import com.club.membership.service.TierEvaluationService;
import com.club.membership.strategy.TierEvaluationStrategy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TierEvaluationServiceImpl implements TierEvaluationService {

    private final TierRuleDao tierRuleDao;
    private final List<TierEvaluationStrategy> strategies;

    public TierEvaluationServiceImpl(
            TierRuleDao tierRuleDao, List<TierEvaluationStrategy> strategies) {
        this.tierRuleDao = tierRuleDao;
        this.strategies = strategies;
    }

    @Override
    public TierType evaluateTier(TierEvaluationRequest request, UserContext userContext) {

        Map<TierType, List<TierRule>> rulesByTier =
                tierRuleDao.getAllRules().stream()
                        .collect(Collectors.groupingBy(TierRule::tierType));

        List<TierType> tiersByPriority =
                Arrays.stream(TierType.values())
                        .sorted((a, b) -> Integer.compare(b.getPriority(), a.getPriority()))
                        .toList();

        for (TierType tierType : tiersByPriority) {

            List<TierRule> rules = rulesByTier.getOrDefault(tierType, List.of());

            if (rules.isEmpty()) {
                continue;
            }

            boolean eligible =
                    rules.stream()
                            .allMatch(
                                    rule ->
                                            strategies.stream()
                                                    .filter(s -> s.supports(rule.ruleType()))
                                                    .findFirst()
                                                    .orElseThrow(
                                                            () ->
                                                                    new IllegalStateException(
                                                                            "No strategy found for rule type: "
                                                                                    + rule
                                                                                            .ruleType()))
                                                    .isEligible(rule, request));

            if (eligible) {
                return tierType;
            }
        }

        return TierType.SILVER;
    }
}
