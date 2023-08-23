plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
}

group = "me.bossm0n5t3r"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

ktlint {
    version = "0.50.0"
}
