plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "me.bossm0n5t3r"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("io.projectreactor:reactor-bom:2022.0.10")
    }
}

dependencies {
    implementation("io.projectreactor:reactor-core")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")

    testImplementation(kotlin("test"))
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
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
