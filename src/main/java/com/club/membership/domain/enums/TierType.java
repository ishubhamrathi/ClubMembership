package com.club.membership.domain.enums;

public enum TierType {
    SILVER(1),
    GOLD(2),
    PLATINUM(3);

    private final int priority;

    TierType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
