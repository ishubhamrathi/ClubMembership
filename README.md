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

* Java 21
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
│     └── enums
├── dto
│     ├── request
│     └── response
├── exception
├── config
└── utils
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
http://localhost:8080/swagger-ui
http://localhost:8080/api-docs

```

---

## Access H2 Database Console(Local Only)

Open:

```text
http://localhost:8080/h2-console
```

Use:

```text
JDBC URL:
jdbc:h2:mem:membershipdb

Username:
sa

Password:
(empty)
```
