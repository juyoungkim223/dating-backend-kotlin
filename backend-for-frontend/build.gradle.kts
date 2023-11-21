import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("org.springframework.boot") version "3.1.3"
    id ("io.spring.dependency-management") version "1.1.3"
    id ("org.jetbrains.kotlin.jvm") version "1.8.22"
    id ("org.jetbrains.kotlin.plugin.spring") version "1.8.22"
    kotlin("kapt") version "1.7.10"
}

group = "com.riti"
version = "0.0.1-SNAPSHOT"



repositories {
    mavenCentral()
}

dependencies {
    // r2dbc
    implementation ("org.springframework.boot:spring-boot-starter-data-r2dbc")
    // webflux
    implementation ("org.springframework.boot:spring-boot-starter-webflux")
    // kafka
    implementation("io.projectreactor.kafka:reactor-kafka:1.3.21")
    // redis reactive
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")

    // serializing
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")
    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")
    // reacitve & coroutines
    implementation ("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation ("org.jetbrains.kotlin:kotlin-reflect")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    developmentOnly ("org.springframework.boot:spring-boot-devtools")
    // mysql
    runtimeOnly ("com.mysql:mysql-connector-j")
    runtimeOnly ("io.asyncer:r2dbc-mysql")
    // lombok
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
    // test
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("io.projectreactor:reactor-test")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    // swagger doc
    implementation ("org.springdoc:springdoc-openapi-starter-webflux-ui:2.0.3")
    // mail
    implementation("org.springframework.boot:spring-boot-starter-mail:2.6.7")
    implementation("jakarta.mail:jakarta.mail-api:2.1.2")
    implementation("org.eclipse.angus:angus-activation:2.0.1")
    implementation("org.eclipse.angus:angus-mail:2.0.2")
    implementation(kotlin("stdlib-jdk8"))
    // mapstruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    kaptTest("org.mapstruct:mapstruct-processor:1.5.5.Final")
    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // hmac
    implementation("commons-codec:commons-codec:1.16.0")
    // fcm
    implementation("com.google.firebase:firebase-admin:9.2.0")
    // akka
    implementation("com.typesafe.akka:akka-actor_3:2.8.5")

    // data module
    api(project(":data"))
}
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<KotlinCompile> {

    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
