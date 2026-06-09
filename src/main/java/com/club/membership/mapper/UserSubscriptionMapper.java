package com.club.membership.mapper;

import com.club.membership.domain.enums.SubscriptionStatus;
import com.club.membership.domain.enums.TierType;
import com.club.membership.domain.model.UserSubscription;
import com.club.membership.jooq.generated.tables.records.UserSubscriptionRecord;
import org.springframework.stereotype.Component;

@Component
public class UserSubscriptionMapper {
    public UserSubscription toDomain(
            UserSubscriptionRecord record
    ) {
        return new UserSubscription(
                record.getId(),
                record.getUserId(),
                record.getMembershipPlanId(),
                TierType.valueOf(record.getTierType()),
                SubscriptionStatus.valueOf(record.getStatus()),
                record.getSubscribedAt(),
                record.getExpiresAt(),
                record.getVersion()
        );
    }
}
