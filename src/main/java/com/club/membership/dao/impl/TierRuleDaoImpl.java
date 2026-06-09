package com.club.membership.dao.impl;

import com.club.membership.context.UserContext;
import com.club.membership.dao.TierRuleDao;
import com.club.membership.domain.enums.RuleType;
import com.club.membership.domain.enums.TierType;
import com.club.membership.domain.model.TierRule;
import com.club.membership.exception.DatabaseException;
import com.club.membership.jooq.generated.Tables;
import com.club.membership.mapper.TierRuleMapper;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class TierRuleDaoImpl implements TierRuleDao {

    private final DSLContext dslContext;
    private final TierRuleMapper mapper;

    @Override
    public List<TierRule> getByTier(TierType tierType, UserContext userContext) {
        return dslContext.selectFrom(Tables.TIER_RULE)
                .where(Tables.TIER_RULE.TIER_TYPE.eq(tierType.name()))
                .fetch()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<TierRule> getByTierAndRuleTyp(TierType tierType, RuleType ruleType, UserContext userContext) {
        return dslContext.selectFrom(Tables.TIER_RULE)
                .where(Tables.TIER_RULE.TIER_TYPE.eq(tierType.name()))
                .and(Tables.TIER_RULE.RULE_TYPE.eq(ruleType.name()))
                .fetchOptional()
                .map(mapper::toDomain);
    }

    @Override
    public TierRule create(TierRule tierRule, UserContext userContext) {
        return dslContext.insertInto(Tables.TIER_RULE)
                .set(Tables.TIER_RULE.TIER_TYPE, tierRule.tierType().name())
                .set(Tables.TIER_RULE.RULE_TYPE, tierRule.ruleType().name())
                .set(Tables.TIER_RULE.THRESHOLD_VALUE, tierRule.threshold())
                .returning()
                .fetchOptional()
                .map(mapper::toDomain)
                .orElseThrow(() -> new DatabaseException("Failed to create tier rule!"));
    }
}
