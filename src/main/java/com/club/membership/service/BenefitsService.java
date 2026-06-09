package com.club.membership.service;

import com.club.membership.context.UserContext;
import com.club.membership.domain.enums.TierType;
import com.club.membership.dto.response.BenefitResponse;
import java.util.List;

public interface BenefitsService {

    List<BenefitResponse> getBenefits(TierType tierType, UserContext userContext);
}
