package com.club.membership.dto.response;

import com.club.membership.domain.enums.SubscriptionStatus;
import com.club.membership.domain.enums.TierType;

import java.time.LocalDateTime;

public record SubscriptionResponse (
        Long subscriptionId,
        Long planId,
        TierType tierType,
        SubscriptionStatus status,
        LocalDateTime subscribedAt,
        LocalDateTime expiresAt,
        Long version
) {
}
