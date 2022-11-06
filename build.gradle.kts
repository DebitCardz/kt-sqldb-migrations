import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "me.tech"
version = "0.0.1"

application {
    mainClass.set("me.tech.MainKt")
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.github.jasync-sql", "jasync-mysql", "2.1.1")

    implementation("org.apache.logging.log4j", "log4j-slf4j-impl", "2.19.0")
}