group = "com.cpoulsen.ktor_graphql_api"

plugins {
    kotlin("jvm") version "1.5.0" apply false
    id("com.expediagroup.graphql") version "4.1.0" apply false

    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("io.kotest") version "0.3.8" apply false
    //  apply false
}

repositories {
    mavenCentral()
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.kotest")

    repositories {
        mavenCentral()
    }
}
