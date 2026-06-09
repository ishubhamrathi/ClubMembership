package com.club.membership.dao;

import com.club.membership.context.UserContext;
import com.club.membership.domain.enums.BenefitType;
import com.club.membership.domain.enums.TierType;
import com.club.membership.domain.model.TierBenefit;
import java.util.List;
import java.util.Optional;

public interface TierBenefitDao {
    List<TierBenefit> getByTier(TierType tierType, UserContext userContext);

    TierBenefit create(TierBenefit tierBenefit, UserContext userContext);

    Optional<TierBenefit> getByTierAndBenefitType(
            TierType tierType, BenefitType benefitType, UserContext userContext);
}
