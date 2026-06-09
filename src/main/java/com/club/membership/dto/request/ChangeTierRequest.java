package com.club.membership.dto.request;

import com.club.membership.domain.enums.TierType;
import jakarta.validation.constraints.NotNull;

public record ChangeTierRequest (
  @NotNull
  TierType tierType
) {
}
