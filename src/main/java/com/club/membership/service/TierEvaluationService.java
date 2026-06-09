package com.club.membership.service;

import com.club.membership.context.UserContext;
import com.club.membership.domain.enums.TierType;
import com.club.membership.dto.request.TierEvaluationRequest;

public interface TierEvaluationService {

    TierType evaluateTier(
            TierEvaluationRequest request,
            UserContext userContext
    );
}
