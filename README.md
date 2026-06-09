# Club Membership Program

A Spring Boot application implementing a configurable membership program with tier-based benefits.

---

## Features

* Membership plans:

    * Monthly
    * Quarterly
    * Yearly

* Membership tiers:

    * Silver
    * Gold
    * Platinum

* Configurable benefits:

    * Free delivery
    * Discount percentage
    * Early access
    * Priority support

* User operations:

    * View membership plans
    * Subscribe to a membership
    * Upgrade/Downgrade membership tier
    * Cancel subscription
    * View current subscription

---

## Tech Stack

* Java 25
* Spring Boot
* jOOQ
* H2 Database
* OpenAPI / Swagger
* Gradle Kotlin DSL

---

## Project Structure

```text
com.club.membership

├── controller
├── service
│     └── impl
├── dao
│     └── impl
├── mapper
├── strategy
├── domain
│     ├── model
│     ├── enums
│     └── event
├── dto
│     ├── request
│     └── response
├── exception
├── config
└── util
```

---

## Architecture

```text
Controller
    ↓
Service
    ↓
DAO
    ↓
jOOQ
    ↓
H2 Database
```

---

## Design Patterns Used

### Strategy Pattern

Used for tier evaluation.

Examples:

* OrderCountStrategy
* TotalOrderValueStrategy
* CohortStrategy

This makes the system extensible and follows Open/Closed Principle.

---

### Builder Pattern

Used for complex domain objects such as:

* UserSubscription

---

### DAO Pattern

Separates database access from business logic.

---

### Mapper Pattern

Two mapper categories:

1. jOOQ Record → Domain Model
2. Domain Model → Response DTO

---

## Concurrency Handling

Optimistic locking is implemented using:

```text
version BIGINT
```

The version column is incremented during updates to prevent lost updates.

---

## Database

H2 in-memory database is used for demo purposes.

Tables:

* membership_plan
* user_subscription
* tier_rule
* tier_benefit

---

## Seed Data

Application automatically loads:

### Membership Plans

* Monthly Membership
* Quarterly Membership
* Yearly Membership

### Tier Benefits

* Silver
* Gold
* Platinum

### Tier Rules

Promotion rules for:

* Gold
* Platinum

---

## Build

```bash
./gradlew clean build
```

---

## Run

```bash
./gradlew bootRun
```

---

## Swagger

Open:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## H2 Console

Open:

```text
http://localhost:8080/h2-console
```

Use:

```text
JDBC URL:
jdbc:h2:mem:testdb

Username:
sa

Password:
(empty)
```

---

## Required Header

All APIs require:

```text
X-User-Id
```

Example:

```text
550e8400-e29b-41d4-a716-446655440000
```

---

# APIs

## Membership Plans

### Get all plans

```http
GET /api/v1/membership-plans
```

### Get plan by id

```http
GET /api/v1/membership-plans/{id}
```

---

## Subscription

### Subscribe

```http
POST /api/v1/subscriptions
```

Example body:

```json
{
  "membershipPlanId": 1,
  "tierType": "SILVER"
}
```

---

### Get current subscription

```http
GET /api/v1/subscriptions/current
```

---

### Change tier

```http
PATCH /api/v1/subscriptions/tier
```

Example:

```json
{
  "tierType": "GOLD"
}
```

---

### Cancel subscription

```http
DELETE /api/v1/subscriptions
```


