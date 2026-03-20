plugins {
    java
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.dre"
version = "0.0.1-SNAPSHOT"
description = "ChatAppServer"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-websocket-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation ("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("com.github.ben-manes.caffeine:caffeine")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("com.nimbusds:nimbus-jose-jwt:10.8")
    implementation("org.springframework.boot:spring-boot-starter-flyway:4.0.2")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("io.netty:netty-all:4.1.99.Final")
    implementation("org.reactivestreams:reactive-streams:1.0.4")
    implementation("io.projectreactor:reactor-core:3.5.9")
    implementation("io.projectreactor.netty:reactor-netty:1.1.7")
}


tasks.withType<Test> {
    useJUnitPlatform()
}
