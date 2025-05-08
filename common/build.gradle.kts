plugins {
    kotlin("plugin.spring") version "1.9.25"
}


dependencies {
    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // commons
    implementation("commons-io:commons-io:2.15.1")

    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
}