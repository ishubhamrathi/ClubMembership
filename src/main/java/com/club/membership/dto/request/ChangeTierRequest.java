package com.club.membership.dto.request;

import com.club.membership.domain.enums.TierType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ChangeTierRequest (
  @NotNull
  TierType tierType,

  @NotNull
  Integer orderCount,

  @NotNull
  BigDecimal totalOrderValue
) {
}
