import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val kotlinVersion: String by project
val graphqlVersion: String by project
val kotlinReflectVersion: String by project
val kotestVersion: String by project
val exposedVersion: String by project
val http4kVersion: String by project

plugins {
    kotlin("jvm")
}

version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinReflectVersion")

    testImplementation("com.expediagroup:graphql-kotlin-schema-generator:$graphqlVersion")
    // Models
    testImplementation(project(":ktor-graphql-template-models"))
    testImplementation("com.google.code.gson:gson:2.8.6")

    // Testing
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-engine-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-api-jvm:$kotestVersion")

    testImplementation("org.http4k:http4k-client-apache:$http4kVersion")
    testImplementation("org.http4k:http4k-graphql:$http4kVersion")

    // Postgres
    testImplementation("org.postgresql:postgresql:42.2.20")
    testImplementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    testImplementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
}

tasks.test {
    onlyIf {
        project.hasProperty("functionalTests")
    }
    dependsOn("kotest")
}

tasks.kotest {
    onlyIf {
        project.hasProperty("functionalTests")
    }
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
