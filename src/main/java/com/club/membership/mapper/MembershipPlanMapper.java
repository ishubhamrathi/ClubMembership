package com.club.membership.mapper;

import com.club.membership.domain.enums.BillingCycle;
import com.club.membership.domain.model.MembershipPlan;
import com.club.membership.jooq.generated.tables.records.MembershipPlanRecord;
import org.springframework.stereotype.Component;

@Component
public class MembershipPlanMapper {
    public MembershipPlan toDomain(MembershipPlanRecord record) {
        return new MembershipPlan(
                record.getId(),
                record.getName(),
                BillingCycle.valueOf(record.getBillingCycle()),
                record.getPrice()
        );

    }
}
