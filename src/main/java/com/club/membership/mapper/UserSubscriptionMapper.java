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
        return UserSubscription.builder()
                .id(record.getId())
                .userId(record.getUserId())
                .membershipPlanId(record.getMembershipPlanId())
                .tierType(TierType.valueOf(record.getTierType()))
                .status(SubscriptionStatus.valueOf(record.getStatus()))
                .subscribedAt(record.getSubscribedAt())
                .expiresAt(record.getExpiresAt())
                .version(record.getVersion())
                .build();
    }
}
