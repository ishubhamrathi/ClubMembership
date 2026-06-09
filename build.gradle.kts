import org.jooq.meta.jaxb.Property

plugins {
    java
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("nu.studer.jooq") version "9.0"
    id("com.diffplug.spotless") version "7.0.2"
}

group = "com.club"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-h2console")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.2")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-validation-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    jooqGenerator("org.jooq:jooq-meta:3.19.18")
    jooqGenerator("org.jooq:jooq-codegen:3.19.18")
    jooqGenerator("org.jooq:jooq-meta-extensions:3.19.18")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jooq {
    version.set("3.19.18")
    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)
            jooqConfiguration.apply {
                generator.apply {
                    database.apply {
                        name = "org.jooq.meta.extensions.ddl.DDLDatabase"
                        properties.add(
                            Property().apply {
                                key = "scripts"
                                value = "src/main/resources/schema.sql"
                            }
                        )
                        properties.add(
                            Property().apply {
                                key = "sort"
                                value = "semantic"
                            }
                        )
                    }
                    target.apply {
                        packageName = "com.club.membership.jooq.generated"
                    }
                }
            }
        }
    }
}

spotless {
    java {
        target("src/*/java/**/*.java")
        targetExclude("**/jooq/**")
        googleJavaFormat("1.22.0").aosp()
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
