package com.club.membership.controller;

import com.club.membership.context.UserContext;
import com.club.membership.domain.enums.TierType;
import com.club.membership.dto.request.TierEvaluationRequest;
import com.club.membership.service.TierEvaluationService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tier-evaluation")
@RequiredArgsConstructor
public class TierEvaluationController {

    private final TierEvaluationService tierEvaluationService;

    @PostMapping
    public TierType evaluateTier(
            @Valid @RequestBody TierEvaluationRequest request,
            @RequestHeader("X-User-Id") UUID userId) {

        return tierEvaluationService.evaluateTier(request, new UserContext(userId));
    }
}
