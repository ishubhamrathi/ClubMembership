package com.club.membership.domain.model;

import com.club.membership.domain.enums.SubscriptionStatus;
import com.club.membership.domain.enums.TierType;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserSubscription {

    private Long id;
    private UUID userId;
    private Long membershipPlanId;
    private TierType tierType;
    private SubscriptionStatus status;
    private LocalDateTime subscribedAt;
    private LocalDateTime expiresAt;
    private Long version;

    private UserSubscription(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.membershipPlanId = builder.membershipPlanId;
        this.tierType = builder.tierType;
        this.status = builder.status;
        this.subscribedAt = builder.subscribedAt;
        this.expiresAt = builder.expiresAt;
        this.version = builder.version;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private UUID userId;
        private Long membershipPlanId;
        private TierType tierType;
        private SubscriptionStatus status;
        private LocalDateTime subscribedAt;
        private LocalDateTime expiresAt;
        private Long version;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder membershipPlanId(Long membershipPlanId) {
            this.membershipPlanId = membershipPlanId;
            return this;
        }

        public Builder tierType(TierType tierType) {
            this.tierType = tierType;
            return this;
        }

        public Builder status(SubscriptionStatus status) {
            this.status = status;
            return this;
        }

        public Builder subscribedAt(LocalDateTime subscribedAt) {
            this.subscribedAt = subscribedAt;
            return this;
        }

        public Builder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder version(Long version) {
            this.version = version;
            return this;
        }

        public UserSubscription build() {
            return new UserSubscription(this);
        }
    }

    public Long getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public Long getMembershipPlanId() {
        return membershipPlanId;
    }

    public TierType getTierType() {
        return tierType;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public LocalDateTime getSubscribedAt() {
        return subscribedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setTierType(TierType tierType) {
        this.tierType = tierType;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}