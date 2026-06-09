package com.club.membership.dao;

import com.club.membership.context.UserContext;
import com.club.membership.domain.model.UserSubscription;

import java.util.Optional;

public interface UserSubscriptionDao {

    Optional<UserSubscription> getById(Long id, UserContext userContext);

    Optional<UserSubscription> getActiveSubscription(UserContext userContext);

    UserSubscription create(UserSubscription subscription, UserContext userContext);

    UserSubscription update(UserSubscription subscription, UserContext userContext);
}