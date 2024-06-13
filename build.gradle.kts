import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.sonarqube") version "4.3.0.3225"

	//JPA 사용에 필요한 기본 생성자 생성
	id("org.jetbrains.kotlin.plugin.jpa") version "1.4.32"

	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
}

group = "com.kkk"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")

	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")

//
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

// 코틀린 리플렉션 라이브러리, 자바처럼 코틀린도 리플렉션을 통해 런타임에 코틀린 프로그램 내부 구조를 파악할 수 있다.
// 런타임 라이브러리의 불필요한 범람을 위해 만들어졌다. 리플렉션은 https://sabarada.tistory.com/190
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("io.github.oshai:kotlin-logging-jvm:5.1.1")

	runtimeOnly("com.mysql:mysql-connector-j")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// 웹 소켓
	implementation("org.springframework.boot:spring-boot-starter-websocket")

	// Redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	//

}



tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
