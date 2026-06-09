package com.club.membership.mapper;

import com.club.membership.domain.model.UserSubscription;
import com.club.membership.dto.response.SubscriptionResponse;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionResponseMapper {

    public SubscriptionResponse toResponse(UserSubscription subscription) {

        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getMembershipPlanId(),
                subscription.getTierType(),
                subscription.getStatus(),
                subscription.getSubscribedAt(),
                subscription.getExpiresAt(),
                subscription.getVersion());
    }
}
