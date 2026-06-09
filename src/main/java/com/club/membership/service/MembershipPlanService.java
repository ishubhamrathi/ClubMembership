package com.club.membership.service;

import com.club.membership.context.UserContext;
import com.club.membership.dto.response.MembershipPlanResponse;

import java.util.List;

public interface MembershipPlanService {

    List<MembershipPlanResponse> getAllPlans(UserContext userContext);

    MembershipPlanResponse getPlan(Long id, UserContext userContext);
}
