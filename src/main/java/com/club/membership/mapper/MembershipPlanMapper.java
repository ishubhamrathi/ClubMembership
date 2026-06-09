package com.club.membership.mapper;

import com.club.membership.domain.enums.BillingCycle;
import com.club.membership.domain.model.MembershipPlan;
import com.club.membership.jooq.generated.tables.records.MembershipPlanRecord;
import org.springframework.stereotype.Component;

@Component
public class MembershipPlanMapper {
    public MembershipPlan toDomain(MembershipPlanRecord membershipPlanRecord) {
        return new MembershipPlan(
                membershipPlanRecord.getId(),
                membershipPlanRecord.getName(),
                BillingCycle.valueOf(membershipPlanRecord.getBillingCycle()),
                membershipPlanRecord.getPrice());
    }
}
