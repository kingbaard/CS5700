import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
//    kotlin("jvm")
//    id("org.jetbrains.compose")
//    id("org.jetbrains.kotlin.plugin.compose")
//    kotlin("jvm") version "2.0.0"
    kotlin("jvm") version "2.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("io.ktor:ktor-server-core-jvm:2.3.12")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.12")
    implementation(kotlin("stdlib-jdk8"))
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ShipmentTracker"
            packageVersion = "1.0.0"
        }
    }
}
kotlin {
    jvmToolchain(21)
}