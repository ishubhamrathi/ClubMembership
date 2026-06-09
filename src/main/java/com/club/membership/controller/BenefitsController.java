package com.club.membership.controller;

import com.club.membership.context.UserContext;
import com.club.membership.domain.enums.TierType;
import com.club.membership.dto.response.BenefitResponse;
import com.club.membership.service.BenefitsService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/benefits")
@RequiredArgsConstructor
public class BenefitsController {

    private final BenefitsService benefitsService;

    @GetMapping("/{tierType}")
    public List<BenefitResponse> getBenefits(
            @PathVariable TierType tierType, @RequestHeader("X-User-Id") UUID userId) {

        return benefitsService.getBenefits(tierType, new UserContext(userId));
    }
}
