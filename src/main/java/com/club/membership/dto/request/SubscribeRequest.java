package com.club.membership.dto.request;

import com.club.membership.domain.enums.TierType;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SubscribeRequest(
    @NotNull
    Long membershipPlanId,

    @NotNull
    TierType tierType
) {

}
