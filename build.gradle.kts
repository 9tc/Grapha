import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
}

group = "io.github._9tc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	maven { url = uri("https://repo.spring.io/release") }
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation:2.6.3")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.6.3")
	implementation("org.springframework.boot:spring-boot-starter-web:2.6.3")
	implementation("org.springframework.boot:spring-boot-starter-jdbc:2.6.3")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
	runtimeOnly("org.postgresql:postgresql:42.3.1")

	implementation(kotlin("stdlib", org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION)) // or "stdlib-jdk8"
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:1.0-M1-1.4.0-rc") // JVM dependency

	testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
	builder = "paketobuildpacks/builder:tiny"
	environment = mapOf("BP_NATIVE_IMAGE" to "true")
}
