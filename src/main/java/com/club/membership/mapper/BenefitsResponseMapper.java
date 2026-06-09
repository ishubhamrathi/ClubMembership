package com.club.membership.mapper;

import com.club.membership.domain.model.TierBenefit;
import com.club.membership.dto.response.BenefitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class BenefitsResponseMapper {

    private final ObjectMapper objectMapper;

    public BenefitResponse toResponse(TierBenefit tierBenefit) {

        Map<String, Object> configuration =
                objectMapper.readValue(
                        tierBenefit.configuration(),
                        new TypeReference<>() {
                        });

        return new BenefitResponse(
                tierBenefit.benefitType(),
                configuration
        );

    }
}