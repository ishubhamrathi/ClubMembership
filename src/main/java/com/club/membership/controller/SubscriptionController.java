package com.club.membership.controller;

import com.club.membership.context.UserContext;
import com.club.membership.dto.request.ChangeTierRequest;
import com.club.membership.dto.request.SubscribeRequest;
import com.club.membership.dto.response.SubscriptionResponse;
import com.club.membership.service.SubscriptionService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionResponse subscribe(
            @Valid @RequestBody SubscribeRequest request, @RequestHeader("X-User-Id") UUID userId) {

        return subscriptionService.subscribe(request, new UserContext(userId));
    }

    @PatchMapping("/tier")
    public SubscriptionResponse changeTier(
            @Valid @RequestBody ChangeTierRequest request,
            @RequestHeader("X-User-Id") UUID userId) {

        return subscriptionService.changeTier(request, new UserContext(userId));
    }

    @GetMapping("/current")
    public SubscriptionResponse getCurrentSubscription(@RequestHeader("X-User-Id") UUID userId) {

        return subscriptionService.getCurrentSubscription(new UserContext(userId));
    }

    @GetMapping
    public List<SubscriptionResponse> getAllSubscriptions(@RequestHeader("X-User-Id") UUID userId) {

        return subscriptionService.getAllSubscriptions(new UserContext(userId));
    }

    @DeleteMapping
    public void cancel(@RequestHeader("X-User-Id") UUID userId) {

        subscriptionService.cancel(new UserContext(userId));
    }
}
