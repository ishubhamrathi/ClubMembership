package com.club.membership.service.impl;

import com.club.membership.context.UserContext;
import com.club.membership.dao.MembershipPlanDao;
import com.club.membership.dao.UserSubscriptionDao;
import com.club.membership.domain.enums.SubscriptionStatus;
import com.club.membership.domain.model.MembershipPlan;
import com.club.membership.domain.model.UserSubscription;
import com.club.membership.dto.request.ChangeTierRequest;
import com.club.membership.dto.request.SubscribeRequest;
import com.club.membership.dto.request.TierEvaluationRequest;
import com.club.membership.dto.response.SubscriptionResponse;
import com.club.membership.exception.InvalidTierException;
import com.club.membership.exception.ResourceNotFoundException;
import com.club.membership.exception.SubscriptionAlreadyExistsException;
import com.club.membership.mapper.SubscriptionResponseMapper;
import com.club.membership.service.SubscriptionService;
import com.club.membership.service.TierEvaluationService;
import com.club.membership.utils.SubscriptionDateUtil;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final MembershipPlanDao membershipPlanDao;
    private final UserSubscriptionDao userSubscriptionDao;
    private final SubscriptionResponseMapper subscriptionResponseMapper;
    private final TierEvaluationService tierEvaluationService;

    @Override
    @Transactional
    public SubscriptionResponse subscribe(SubscribeRequest request, UserContext userContext) {

        MembershipPlan membershipPlan =
                membershipPlanDao
                        .getById(request.membershipPlanId(), userContext)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Membership plan not found"));

        userSubscriptionDao
                .getActiveSubscription(userContext)
                .ifPresent(
                        subscription -> {
                            throw new SubscriptionAlreadyExistsException(
                                    "Active subscription already exists");
                        });

        LocalDateTime subscribedAt = LocalDateTime.now();

        LocalDateTime expiresAt =
                SubscriptionDateUtil.calculateExpiryDate(
                        membershipPlan.billingCycle(), subscribedAt);

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
                userSubscriptionDao.create(subscription, userContext);

        return subscriptionResponseMapper.toResponse(createdSubscription);
    }

    @Override
    @Transactional
    public SubscriptionResponse changeTier(ChangeTierRequest request, UserContext userContext) {

        UserSubscription subscription =
                userSubscriptionDao
                        .getActiveSubscription(userContext)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Active subscription not found"));

        if (subscription.getTierType() == request.tierType()) {
            throw new InvalidTierException("New tier is the same as current tier");
        }

        TierEvaluationRequest evaluationRequest =
                new TierEvaluationRequest(request.orderCount(), request.totalOrderValue(), null);

        var evaluatedTier = tierEvaluationService.evaluateTier(evaluationRequest, userContext);

        if (evaluatedTier.getPriority() < request.tierType().getPriority()) {
            throw new InvalidTierException(
                    "Not eligible for "
                            + request.tierType()
                            + " tier. Highest eligible tier: "
                            + evaluatedTier);
        }

        subscription.setTierType(request.tierType());

        UserSubscription updatedSubscription =
                userSubscriptionDao.update(subscription, userContext);

        return subscriptionResponseMapper.toResponse(updatedSubscription);
    }

    @Override
    public SubscriptionResponse getCurrentSubscription(UserContext userContext) {

        UserSubscription subscription =
                userSubscriptionDao
                        .getActiveSubscription(userContext)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Active subscription not found"));

        return subscriptionResponseMapper.toResponse(subscription);
    }

    @Override
    public List<SubscriptionResponse> getAllSubscriptions(UserContext userContext) {

        return userSubscriptionDao.getAllByUser(userContext).stream()
                .map(subscriptionResponseMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void cancel(UserContext userContext) {

        UserSubscription subscription =
                userSubscriptionDao
                        .getActiveSubscription(userContext)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Active subscription not found"));

        subscription.setStatus(SubscriptionStatus.CANCELLED);

        userSubscriptionDao.update(subscription, userContext);
    }
}
