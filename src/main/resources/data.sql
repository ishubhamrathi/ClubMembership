DELETE FROM tier_benefit;
DELETE FROM tier_rule;
DELETE FROM membership_plan;

-- Membership Plans
INSERT INTO membership_plan (
    name,
    billing_cycle,
    price
)
VALUES
(
    'Monthly Membership',
    'MONTHLY',
    99.00
),
(
    'Quarterly Membership',
    'QUARTERLY',
    249.00
),
(
    'Yearly Membership',
    'YEARLY',
    899.00
);

-- Tier Rules
INSERT INTO tier_rule (
    tier_type,
    rule_type,
    threshold_value
)
VALUES
(
    'GOLD',
    'ORDER_COUNT',
    '5'
),
(
    'GOLD',
    'TOTAL_ORDER_VALUE',
    '5000'
),
(
    'PLATINUM',
    'ORDER_COUNT',
    '10'
),
(
    'PLATINUM',
    'TOTAL_ORDER_VALUE',
    '10000'
);

-- SILVER Benefits
INSERT INTO tier_benefit (
    tier_type,
    benefit_type,
    configuration
)
VALUES
(
    'SILVER',
    'FREE_DELIVERY',
    '{"minimumOrderValue":500}'
),
(
    'SILVER',
    'EARLY_ACCESS',
    '{"enabled":false}'
);

-- GOLD Benefits
INSERT INTO tier_benefit (
    tier_type,
    benefit_type,
    configuration
)
VALUES
(
    'GOLD',
    'FREE_DELIVERY',
    '{"minimumOrderValue":0}'
),
(
    'GOLD',
    'DISCOUNT_PERCENT',
    '{"percentage":5}'
),
(
    'GOLD',
    'EARLY_ACCESS',
    '{"enabled":true}'
);

-- PLATINUM Benefits
INSERT INTO tier_benefit (
    tier_type,
    benefit_type,
    configuration
)
VALUES
(
    'PLATINUM',
    'FREE_DELIVERY',
    '{"minimumOrderValue":0}'
),
(
    'PLATINUM',
    'DISCOUNT_PERCENT',
    '{"percentage":10}'
),
(
    'PLATINUM',
    'EARLY_ACCESS',
    '{"enabled":true}'
),
(
    'PLATINUM',
    'PRIORITY_SUPPORT',
    '{"enabled":true}'
);
