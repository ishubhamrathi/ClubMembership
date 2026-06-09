package com.club.membership.mapper;

import com.club.membership.domain.enums.BenefitType;
import com.club.membership.domain.enums.TierType;
import com.club.membership.domain.model.TierBenefit;
import com.club.membership.jooq.generated.tables.records.TierBenefitRecord;
import org.springframework.stereotype.Component;

@Component
public class TierBenefitMapper {
    public TierBenefit toDomain(TierBenefitRecord tierBenefitRecord) {
        return new TierBenefit(
                tierBenefitRecord.getId(),
                TierType.valueOf(tierBenefitRecord.getTierType()),
                BenefitType.valueOf(tierBenefitRecord.getBenefitType()),
                tierBenefitRecord.getConfiguration()
        );
    }
}
