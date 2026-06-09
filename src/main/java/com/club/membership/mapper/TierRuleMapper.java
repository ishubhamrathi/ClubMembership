package com.club.membership.mapper;

import com.club.membership.domain.enums.RuleType;
import com.club.membership.domain.enums.TierType;
import com.club.membership.domain.model.TierRule;
import com.club.membership.jooq.generated.tables.records.TierRuleRecord;
import org.springframework.stereotype.Component;

@Component
public class TierRuleMapper {
    public TierRule toDomain(TierRuleRecord tierRuleRecord) {
        return new TierRule(
                tierRuleRecord.getId(),
                TierType.valueOf(tierRuleRecord.getTierType()),
                RuleType.valueOf(tierRuleRecord.getRuleType()),
                tierRuleRecord.getThresholdValue());
    }
}
