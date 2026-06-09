package com.club.membership.dao.impl;

import com.club.membership.context.UserContext;
import com.club.membership.dao.TierBenefitDao;
import com.club.membership.domain.enums.BenefitType;
import com.club.membership.domain.enums.TierType;
import com.club.membership.domain.model.TierBenefit;
import com.club.membership.exception.DatabaseException;
import com.club.membership.jooq.generated.Tables;
import com.club.membership.mapper.TierBenefitMapper;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TierBenefitDaoImpl implements TierBenefitDao {

    private final DSLContext dslContext;
    private final TierBenefitMapper mapper;

    @Override
    public List<TierBenefit> getByTier(TierType tierType, UserContext userContext) {
        return dslContext.selectFrom(Tables.TIER_BENEFIT)
                .where(Tables.TIER_BENEFIT.TIER_TYPE.eq(tierType.name()))
                .fetch()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public TierBenefit create(TierBenefit tierBenefit, UserContext userContext) {
        return dslContext.insertInto(Tables.TIER_BENEFIT)
                .set(Tables.TIER_BENEFIT.TIER_TYPE, tierBenefit.tierType().name())
                .set(Tables.TIER_BENEFIT.BENEFIT_TYPE, tierBenefit.benefitType().name())
                .set(Tables.TIER_BENEFIT.CONFIGURATION, tierBenefit.configuration())
                .returning()
                .fetchOptional()
                .map(mapper::toDomain)
                .orElseThrow(() -> new DatabaseException("Failed to create tier benefit"));
    }

    @Override
    public Optional<TierBenefit> getByTierAndBenefitType(TierType tierType, BenefitType benefitType, UserContext userContext) {
        return dslContext.selectFrom(Tables.TIER_BENEFIT)
                .where(Tables.TIER_BENEFIT.TIER_TYPE.eq(tierType.name()))
                .and(Tables.TIER_BENEFIT.BENEFIT_TYPE.eq(benefitType.name()))
                .fetchOptional()
                .map(mapper::toDomain);
    }
}
