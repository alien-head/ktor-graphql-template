import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val logbackVersion: String by project
val kotlinReflectVersion: String by project
val exposedVersion: String by project
val graphqlVersion: String by project

plugins {
    kotlin("jvm")
    `maven-publish`
}

version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinReflectVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("com.expediagroup:graphql-kotlin-schema-generator:$graphqlVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cpoulsen.ktor_graphql_template"
            artifactId = "models"
            version = "1.0"

            from(components["java"])
        }
    }
}
