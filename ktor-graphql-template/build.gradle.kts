import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val graphqlVersion: String by project
val exposedVersion: String by project
val kotlinReflectVersion: String by project

plugins {
    application
    kotlin("jvm")
    id("com.expediagroup.graphql")
}

version = "0.0.1"
application {
    mainClass.set("com.cpoulsen.ktor_graphql_template.api.ApplicationKt")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // Ktor
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinReflectVersion")

    // API models
    implementation(project(":ktor-graphql-template-models"))

    // GraphQL
    implementation("com.expediagroup:graphql-kotlin-server:$graphqlVersion")
    implementation("com.expediagroup:graphql-kotlin-federation:$graphqlVersion")

    // Postgres
    runtimeOnly("org.postgresql:postgresql:42.2.20")
    implementation("org.flywaydb:flyway-core:6.5.2")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("com.zaxxer:HikariCP:4.0.3")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    manifest {
        attributes["Main-Class"] = "io.alienhead.ktor_graphql_template.api.ApplicationKt"
    }
    from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
