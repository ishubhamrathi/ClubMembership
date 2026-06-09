#!/bin/bash

BASE_URL=http://localhost:8080
USER_ID=550e8400-e29b-41d4-a716-446655440000

echo "===== Get Plans ====="
curl -s -X GET
"$BASE_URL/api/v1/membership-plans"
-H "X-User-Id: $USER_ID"

echo
echo "===== Subscribe ====="
curl -s -X POST
"$BASE_URL/api/v1/subscriptions"
-H "Content-Type: application/json"
-H "X-User-Id: $USER_ID"
-d '{
"membershipPlanId": 1,
"tierType": "SILVER"
}'

echo
echo "===== Current Subscription ====="
curl -s -X GET
"$BASE_URL/api/v1/subscriptions/current"
-H "X-User-Id: $USER_ID"

echo
echo "===== Upgrade Tier ====="
curl -s -X PATCH
"$BASE_URL/api/v1/subscriptions/tier"
-H "Content-Type: application/json"
-H "X-User-Id: $USER_ID"
-d '{
"tierType": "GOLD"
}'

echo
echo "===== Benefits ====="
curl -s -X GET
"$BASE_URL/api/v1/benefits/GOLD"
-H "X-User-Id: $USER_ID"

echo
echo "===== Tier Evaluation ====="
curl -s -X POST
"$BASE_URL/api/v1/tier-evaluation"
-H "Content-Type: application/json"
-H "X-User-Id: $USER_ID"
-d '{
"orderCount": 12,
"totalOrderValue": 15000,
"cohort": "PREMIUM"
}'

echo
echo "===== Cancel Subscription ====="
curl -s -X DELETE
"$BASE_URL/api/v1/subscriptions"
-H "X-User-Id: $USER_ID"

echo
echo "===== Done ====="
