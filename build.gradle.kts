plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("kapt") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "org.chat"
version = "0.0.1-SNAPSHOT"

allprojects {
	apply(plugin = "kotlin")
	apply(plugin = "io.spring.dependency-management")

	java {
		toolchain {
			languageVersion = JavaLanguageVersion.of(21)
		}
	}

	repositories {
		mavenCentral()
	}

	kotlin {
		compilerOptions {
			freeCompilerArgs.addAll("-Xjsr305=strict")
		}
	}
}

subprojects {
	apply(plugin = "org.springframework.boot")
	apply(plugin = "kotlin-spring")

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")

		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

		// test
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	}

	tasks.getByName("bootJar") {
		enabled = false
	}

	tasks.getByName("jar") {
		enabled = true
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}
}
