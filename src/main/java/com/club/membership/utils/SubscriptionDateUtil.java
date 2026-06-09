package com.club.membership.utils;

import com.club.membership.domain.enums.BillingCycle;

import java.time.LocalDateTime;

public final class SubscriptionDateUtil {

    private SubscriptionDateUtil() {
    }

    public static LocalDateTime calculateExpiryDate(
            BillingCycle billingCycle,
            LocalDateTime subscribedAt
    ) {

        return switch (billingCycle) {

            case MONTHLY ->
                    subscribedAt.plusMonths(1);

            case QUARTERLY ->
                    subscribedAt.plusMonths(3);

            case YEARLY ->
                    subscribedAt.plusYears(1);
        };
    }
}
