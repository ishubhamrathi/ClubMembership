package com.club.membership.dao.impl;

import com.club.membership.context.UserContext;
import com.club.membership.dao.UserSubscriptionDao;
import com.club.membership.domain.enums.SubscriptionStatus;
import com.club.membership.domain.model.UserSubscription;
import com.club.membership.exception.DatabaseException;
import com.club.membership.jooq.generated.Tables;
import com.club.membership.mapper.UserSubscriptionMapper;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserSubscriptionDaoImpl implements UserSubscriptionDao {

    private final DSLContext dslContext;
    private final UserSubscriptionMapper mapper;

    @Override
    public Optional<UserSubscription> getById(Long id, UserContext userContext) {
        return dslContext
                .selectFrom(Tables.USER_SUBSCRIPTION)
                .where(Tables.USER_SUBSCRIPTION.ID.eq(id))
                .fetchOptional()
                .map(mapper::toDomain);
    }

    @Override
    public Optional<UserSubscription> getActiveSubscription(UserContext userContext) {
        return dslContext
                .selectFrom(Tables.USER_SUBSCRIPTION)
                .where(Tables.USER_SUBSCRIPTION.USER_ID.eq(userContext.userId()))
                .and(
                        Tables.USER_SUBSCRIPTION.STATUS.eq(
                                SubscriptionStatus.ACTIVE.name()
                        )
                )
                .fetchOptional()
                .map(mapper::toDomain);
    }

    @Override
    public UserSubscription create(UserSubscription subscription, UserContext userContext) {
        return dslContext
                .insertInto(Tables.USER_SUBSCRIPTION)
                .set(Tables.USER_SUBSCRIPTION.USER_ID, userContext.userId())
                .set(
                        Tables.USER_SUBSCRIPTION.MEMBERSHIP_PLAN_ID,
                        subscription.getMembershipPlanId()
                )
                .set(
                        Tables.USER_SUBSCRIPTION.TIER_TYPE,
                        subscription.getTierType().name()
                )
                .set(
                        Tables.USER_SUBSCRIPTION.STATUS,
                        subscription.getStatus().name()
                )
                .set(
                        Tables.USER_SUBSCRIPTION.SUBSCRIBED_AT,
                        subscription.getSubscribedAt()
                )
                .set(
                        Tables.USER_SUBSCRIPTION.EXPIRES_AT,
                        subscription.getExpiresAt()
                )
                .set(
                        Tables.USER_SUBSCRIPTION.VERSION,
                        subscription.getVersion()
                )
                .returning()
                .fetchOptional()
                .map(mapper::toDomain)
                .orElseThrow(() -> new DatabaseException("Failed to create subscription"));
    }

    @Override
    public UserSubscription update(UserSubscription subscription, UserContext userContext) {
        int rowsUpdated = dslContext
                .update(Tables.USER_SUBSCRIPTION)
                .set(Tables.USER_SUBSCRIPTION.TIER_TYPE, subscription.getTierType().name())
                .set(Tables.USER_SUBSCRIPTION.STATUS, subscription.getStatus().name())
                .set(Tables.USER_SUBSCRIPTION.EXPIRES_AT, subscription.getExpiresAt())
                .set(Tables.USER_SUBSCRIPTION.VERSION, subscription.getVersion() + 1)
                .where(Tables.USER_SUBSCRIPTION.ID.eq(subscription.getId()))
                .and(Tables.USER_SUBSCRIPTION.VERSION.eq(subscription.getVersion())
                )
                .execute();

        if (rowsUpdated == 0) {
            throw new DatabaseException(
                    "Concurrent modification detected"
            );
        }

        return getById(
                subscription.getId(),
                userContext
        ).orElseThrow();
    }
}
