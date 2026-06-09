package com.club.membership.controller;

import com.club.membership.context.UserContext;
import com.club.membership.domain.enums.TierType;
import com.club.membership.dto.response.BenefitResponse;
import com.club.membership.service.BenefitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/benefits")
@RequiredArgsConstructor
public class BenefitsController {

    private final BenefitsService benefitsService;

    @GetMapping("/{tierType}")
    public List<BenefitResponse> getBenefits(
            @PathVariable TierType tierType,
            @RequestHeader("X-User-Id") UUID userId
    ) {

        return benefitsService.getBenefits(
                tierType,
                new UserContext(userId)
        );
    }
}
