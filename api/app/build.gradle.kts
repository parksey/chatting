plugins {
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot")
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {

    val jjwtVersion = "0.11.5"

    implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")

    implementation(project(":core"))
    implementation(project(":auth:core"))
    implementation(project(":common"))
//    implementation(project(":support:logging"))
//    implementation(project(":support:monitoring"))

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
