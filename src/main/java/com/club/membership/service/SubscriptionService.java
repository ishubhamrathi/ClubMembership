package com.club.membership.service;

import com.club.membership.context.UserContext;
import com.club.membership.dto.request.ChangeTierRequest;
import com.club.membership.dto.request.SubscribeRequest;
import com.club.membership.dto.response.SubscriptionResponse;
import org.jooq.impl.QOM;

public interface SubscriptionService {

    SubscriptionResponse subscribe(SubscribeRequest request, UserContext userContext);

    SubscriptionResponse changeTier(ChangeTierRequest request, UserContext userContext);

    SubscriptionResponse getCurrentSubscription(UserContext userContext);

    void cancel(UserContext userContext);

}
