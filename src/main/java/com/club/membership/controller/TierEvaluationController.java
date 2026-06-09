package com.club.membership.controller;

import com.club.membership.context.UserContext;
import com.club.membership.domain.enums.TierType;
import com.club.membership.dto.request.TierEvaluationRequest;
import com.club.membership.service.TierEvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tier-evaluation")
@RequiredArgsConstructor
public class TierEvaluationController {

    private final TierEvaluationService tierEvaluationService;

    @PostMapping
    public TierType evaluateTier(
            @Valid @RequestBody TierEvaluationRequest request,
            @RequestHeader("X-User-Id") UUID userId
    ) {

        return tierEvaluationService.evaluateTier(
                request,
                new UserContext(userId)
        );
    }
}
