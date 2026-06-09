package com.club.membership.dao;

import com.club.membership.context.UserContext;
import com.club.membership.domain.enums.RuleType;
import com.club.membership.domain.enums.TierType;
import com.club.membership.domain.model.TierRule;
import java.util.List;
import java.util.Optional;

public interface TierRuleDao {

    List<TierRule> getByTier(TierType tierType, UserContext userContext);

    List<TierRule> getAllRules();

    Optional<TierRule> getByTierAndRuleType(
            TierType tierType, RuleType ruleType, UserContext userContext);

    TierRule create(TierRule tierRule, UserContext userContext);
}
