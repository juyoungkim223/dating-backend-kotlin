import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("org.springframework.boot") version "3.1.3"
    id ("io.spring.dependency-management") version "1.1.3"
    id ("org.jetbrains.kotlin.jvm") version "1.8.22"
    id ("org.jetbrains.kotlin.plugin.spring") version "1.8.22"
}

group = "com.riti"
version = "0.0.1-SNAPSHOT"



repositories {
    mavenCentral()
}

dependencies {
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation ("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.jetbrains.kotlin:kotlin-reflect")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    compileOnly ("org.projectlombok:lombok")
    // swagger doc
    implementation ("org.springdoc:springdoc-openapi-starter-webflux-ui:2.0.3")
    // hmac
    implementation("commons-codec:commons-codec:1.16.0")
    // gson
    implementation("com.google.code.gson:gson:2.10.1")


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
