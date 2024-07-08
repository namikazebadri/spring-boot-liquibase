plugins {
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.5"
	id("org.liquibase.gradle") version "2.1.1"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
}

group = "com.unisbadri"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

buildscript {
	dependencies {
		classpath("org.liquibase:liquibase-gradle-plugin:2.2.1")
	}
}

repositories {
	mavenCentral()
	google()
}

liquibase {
	activities.register("main") {
		val liquibaseDbUrl by project.extra.properties
		val liquibaseDbUsername by project.extra.properties
		val liquibaseDbPass by project.extra.properties
		val liquibaseDbSchema by project.extra.properties
		val liquibaseChangelogFile by project.extra.properties
		val liquibaseLogLevel by project.extra.properties
		this.arguments = mapOf(
			"logLevel" to liquibaseLogLevel,
			"changelogFile" to liquibaseChangelogFile,
			"url" to liquibaseDbUrl,
			"defaultSchemaName" to liquibaseDbSchema,
			"username" to liquibaseDbUsername,
			"password" to liquibaseDbPass
		)
	}

	runList = "main"
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.liquibase:liquibase-core")

	runtimeOnly("org.postgresql:postgresql")

	liquibaseRuntime("org.liquibase:liquibase-core:4.23.1")
	liquibaseRuntime("org.postgresql:postgresql:42.7.2")
	liquibaseRuntime("info.picocli:picocli:4.6.1")
	liquibaseRuntime("org.yaml:snakeyaml:2.2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
