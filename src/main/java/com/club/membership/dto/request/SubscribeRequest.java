package com.club.membership.dto.request;

import com.club.membership.domain.enums.TierType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SubscribeRequest(
    @NotNull
    @Min(1)
    Long membershipPlanId,

    @NotNull
    TierType tierType
) {

}
