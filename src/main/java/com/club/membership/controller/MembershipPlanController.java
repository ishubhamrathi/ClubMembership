package com.club.membership.controller;

import com.club.membership.context.UserContext;
import com.club.membership.dto.response.MembershipPlanResponse;
import com.club.membership.service.MembershipPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/membership-plans")
@RequiredArgsConstructor
public class MembershipPlanController {

    private final MembershipPlanService membershipPlanService;

    @GetMapping
    public List<MembershipPlanResponse> getAllPlans(
            @RequestHeader("X-User-Id") UUID userId
    ) {

        return membershipPlanService.getAllPlans(
                new UserContext(userId)
        );
    }

    @GetMapping("/{id}")
    public MembershipPlanResponse getPlan(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") UUID userId
    ) {

        return membershipPlanService.getPlan(
                id,
                new UserContext(userId)
        );
    }
}