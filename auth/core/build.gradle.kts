plugins {
    kotlin("plugin.spring") version "1.9.25"
}


dependencies {
    implementation(project(":common"))

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    //security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // commons
    implementation("commons-io:commons-io:2.15.1")

}