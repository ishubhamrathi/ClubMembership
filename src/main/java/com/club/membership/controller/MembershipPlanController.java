package com.club.membership.controller;

import com.club.membership.context.UserContext;
import com.club.membership.dto.response.MembershipPlanResponse;
import com.club.membership.service.MembershipPlanService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/membership-plans")
public class MembershipPlanController {

    private final MembershipPlanService membershipPlanService;

    public MembershipPlanController(MembershipPlanService membershipPlanService) {
        this.membershipPlanService = membershipPlanService;
    }

    @GetMapping
    public List<MembershipPlanResponse> getAllPlans(@RequestHeader("X-User-Id") UUID userId) {

        return membershipPlanService.getAllPlans(new UserContext(userId));
    }

    @GetMapping("/{id}")
    public MembershipPlanResponse getPlan(
            @PathVariable Long id, @RequestHeader("X-User-Id") UUID userId) {

        return membershipPlanService.getPlan(id, new UserContext(userId));
    }
}
