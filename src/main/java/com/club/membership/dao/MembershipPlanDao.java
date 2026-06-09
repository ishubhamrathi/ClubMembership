package com.club.membership.dao;

import com.club.membership.domain.model.MembershipPlan;
import com.club.membership.context.UserContext;

import java.util.List;
import java.util.Optional;

public interface MembershipPlanDao {
    List<MembershipPlan> getAll(UserContext userContext);

    Optional<MembershipPlan> getById(Long id, UserContext userContext);

    MembershipPlan create(MembershipPlan membershipPlan, UserContext userContext);
}
