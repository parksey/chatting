plugins {
    kotlin("plugin.allopen")
    kotlin("plugin.jpa") version "1.9.25"
    kotlin("kapt") version "1.9.25"

    `java-test-fixtures`
}

allOpen {
    annotations("org.springframework.stereotype.Service")
    annotations("org.springframework.stereotype.Repository")
}

dependencies {
//    implementation(project(":common"))
//    implementation(project(":support:logging"))

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // h2 - local, default
    runtimeOnly("com.h2database:h2")

    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")

    // p6spy
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.10.0")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:${dependencyManagement.importedProperties["querydsl.version"]}:jakarta")
    implementation("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties["querydsl.version"]}:jakarta")
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("jakarta.annotation:jakarta.annotation-api")
    kapt("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties["querydsl.version"]}:jakarta")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // test fixture
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")
}