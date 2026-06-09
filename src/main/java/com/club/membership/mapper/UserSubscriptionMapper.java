package com.club.membership.mapper;

import com.club.membership.domain.enums.SubscriptionStatus;
import com.club.membership.domain.enums.TierType;
import com.club.membership.domain.model.UserSubscription;
import com.club.membership.jooq.generated.tables.records.UserSubscriptionRecord;
import org.springframework.stereotype.Component;

@Component
public class UserSubscriptionMapper {
    public UserSubscription toDomain(UserSubscriptionRecord userSubscriptionRecord) {
        return UserSubscription.builder()
                .id(userSubscriptionRecord.getId())
                .userId(userSubscriptionRecord.getUserId())
                .membershipPlanId(userSubscriptionRecord.getMembershipPlanId())
                .tierType(TierType.valueOf(userSubscriptionRecord.getTierType()))
                .status(SubscriptionStatus.valueOf(userSubscriptionRecord.getStatus()))
                .subscribedAt(userSubscriptionRecord.getSubscribedAt())
                .expiresAt(userSubscriptionRecord.getExpiresAt())
                .version(userSubscriptionRecord.getVersion())
                .build();
    }
}
