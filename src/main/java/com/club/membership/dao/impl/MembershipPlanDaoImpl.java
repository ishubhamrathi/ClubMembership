package com.club.membership.dao.impl;

import com.club.membership.dao.MembershipPlanDao;
import com.club.membership.domain.model.MembershipPlan;
import com.club.membership.context.UserContext;
import com.club.membership.exception.DatabaseException;
import com.club.membership.jooq.generated.Tables;
import com.club.membership.mapper.MembershipPlanMapper;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MembershipPlanDaoImpl implements MembershipPlanDao {

    private final DSLContext dslContext;
    private final MembershipPlanMapper mapper;

    @Override
    public List<MembershipPlan> getAll(UserContext userContext) {
        return dslContext.selectFrom(Tables.MEMBERSHIP_PLAN)
                .fetch()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<MembershipPlan> getById(Long id, UserContext userContext) {
        return dslContext.selectFrom(Tables.MEMBERSHIP_PLAN)
                .where(Tables.MEMBERSHIP_PLAN.ID.eq(id))
                .fetchOptional()
                .map(mapper::toDomain);
    }

    @Override
    public MembershipPlan create(MembershipPlan membershipPlan, UserContext userContext) {
        return dslContext.insertInto(Tables.MEMBERSHIP_PLAN)
                .set(Tables.MEMBERSHIP_PLAN.NAME, membershipPlan.name())
                .set(Tables.MEMBERSHIP_PLAN.BILLING_CYCLE, membershipPlan.billingCycle().name())
                .set(Tables.MEMBERSHIP_PLAN.PRICE, membershipPlan.price())
                .returning()
                .fetchOptional()
                .map(mapper::toDomain)
                .orElseThrow(() -> new DatabaseException("Failed to create membership plan"));
    }
}
