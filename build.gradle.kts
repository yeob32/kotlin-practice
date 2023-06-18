plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "com.example.demo"
version = "1.0-SNAPSHOT"

val coroutineVersion = "1.7.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}