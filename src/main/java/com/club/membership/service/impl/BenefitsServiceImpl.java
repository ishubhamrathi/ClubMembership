package com.club.membership.service.impl;

import com.club.membership.context.UserContext;
import com.club.membership.dao.TierBenefitDao;
import com.club.membership.domain.enums.TierType;
import com.club.membership.dto.response.BenefitResponse;
import com.club.membership.mapper.BenefitsResponseMapper;
import com.club.membership.service.BenefitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BenefitsServiceImpl implements BenefitsService {

    private final TierBenefitDao tierBenefitDao;
    private final BenefitsResponseMapper mapper;

    @Override
    public List<BenefitResponse> getBenefits(
            TierType tierType,
            UserContext userContext
    ) {

        return tierBenefitDao.getByTier(
                        tierType,
                        userContext
                )
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}