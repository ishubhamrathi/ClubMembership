package com.club.membership.service.impl;

import com.club.membership.context.UserContext;
import com.club.membership.dao.MembershipPlanDao;
import com.club.membership.dao.UserSubscriptionDao;
import com.club.membership.domain.enums.SubscriptionStatus;
import com.club.membership.domain.model.MembershipPlan;
import com.club.membership.domain.model.UserSubscription;
import com.club.membership.dto.request.ChangeTierRequest;
import com.club.membership.dto.request.SubscribeRequest;
import com.club.membership.dto.response.SubscriptionResponse;
import com.club.membership.exception.ResourceNotFoundException;
import com.club.membership.exception.SubscriptionAlreadyExistsException;
import com.club.membership.mapper.SubscriptionResponseMapper;
import com.club.membership.service.SubscriptionService;
import com.club.membership.utils.SubscriptionDateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final MembershipPlanDao membershipPlanDao;
    private final UserSubscriptionDao userSubscriptionDao;
    private final SubscriptionResponseMapper subscriptionResponseMapper;

    @Override
    public SubscriptionResponse subscribe(
            SubscribeRequest request,
            UserContext userContext
    ) {

        membershipPlanDao.getById(
                        request.membershipPlanId(),
                        userContext
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Membership plan not found"
                        ));

        userSubscriptionDao.getActiveSubscription(userContext)
                .ifPresent(subscription -> {
                    throw new SubscriptionAlreadyExistsException(
                            "Active subscription already exists"
                    );
                });

        LocalDateTime subscribedAt = LocalDateTime.now();

        MembershipPlan membershipPlan =
                membershipPlanDao.getById(
                                request.membershipPlanId(),
                                userContext
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Membership plan not found"
                                ));

        LocalDateTime expiresAt =
                SubscriptionDateUtil.calculateExpiryDate(
                        membershipPlan.billingCycle(),
                        subscribedAt
                );

        UserSubscription subscription =
                UserSubscription.builder()
                        .userId(userContext.userId())
                        .membershipPlanId(request.membershipPlanId())
                        .tierType(request.tierType())
                        .status(SubscriptionStatus.ACTIVE)
                        .subscribedAt(subscribedAt)
                        .expiresAt(expiresAt)
                        .version(0L)
                        .build();

        UserSubscription createdSubscription =
                userSubscriptionDao.create(
                        subscription,
                        userContext
                );

        return subscriptionResponseMapper.toResponse(
                createdSubscription
        );
    }

    @Override
    public SubscriptionResponse changeTier(
            ChangeTierRequest request,
            UserContext userContext
    ) {

        UserSubscription subscription =
                userSubscriptionDao.getActiveSubscription(userContext)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Active subscription not found"
                                ));

        subscription.setTierType(request.tierType());

        UserSubscription updatedSubscription =
                userSubscriptionDao.update(
                        subscription,
                        userContext
                );

        return subscriptionResponseMapper.toResponse(
                updatedSubscription
        );
    }

    @Override
    public SubscriptionResponse getCurrentSubscription(
            UserContext userContext
    ) {

        UserSubscription subscription =
                userSubscriptionDao.getActiveSubscription(userContext)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Active subscription not found"
                                ));

        return subscriptionResponseMapper.toResponse(
                subscription
        );
    }

    @Override
    public void cancel(
            UserContext userContext
    ) {

        UserSubscription subscription =
                userSubscriptionDao.getActiveSubscription(userContext)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Active subscription not found"
                                ));

        subscription.setStatus(
                SubscriptionStatus.CANCELLED
        );

        userSubscriptionDao.update(
                subscription,
                userContext
        );
    }
}
