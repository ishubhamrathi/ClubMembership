package com.club.membership.service.impl;

import com.club.membership.context.UserContext;
import com.club.membership.dao.MembershipPlanDao;
import com.club.membership.dto.response.MembershipPlanResponse;
import com.club.membership.exception.ResourceNotFoundException;
import com.club.membership.mapper.MembershipPlanResponseMapper;
import com.club.membership.service.MembershipPlanService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipPlanServiceImpl implements MembershipPlanService {

    private final MembershipPlanDao membershipPlanDao;
    private final MembershipPlanResponseMapper mapper;

    @Override
    public List<MembershipPlanResponse> getAllPlans(UserContext userContext) {

        return membershipPlanDao.getAll(userContext).stream().map(mapper::toResponse).toList();
    }

    @Override
    public MembershipPlanResponse getPlan(Long id, UserContext userContext) {

        return membershipPlanDao
                .getById(id, userContext)
                .map(mapper::toResponse)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Membership plan not found for id: " + id));
    }
}
