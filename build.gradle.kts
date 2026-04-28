plugins {
    `java-library`
    id("com.gradleup.shadow") version "9.0.0-rc2"
}

group = "me.teakivy"
version = "2.1.2-hxrry.1"
description = "150+ Toggleable Tweaks & Features including Vanilla Tweaks as a plugin, and more!"
java.sourceCompatibility = JavaVersion.VERSION_25

val outputDir: String? by project

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }

    maven {
        url = uri("https://libraries.minecraft.net/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }

    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }

    maven {
        name = "sonatype-oss-snapshots1"
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
    compileOnly("me.clip:placeholderapi:2.11.6")
}

artifacts {
    archives(tasks.shadowJar.get())
}

tasks.jar {
    val customDir = outputDir ?: layout.buildDirectory.dir("libs")

    archiveFileName.set("TeaksTweaks-v${project.version}.jar")
    destinationDirectory.set(file(customDir))
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand("version" to project.version)
    }

    filesMatching("paper-plugin.yml") {
        expand("version" to project.version)
    }

    filesMatching("config.yml") {
        expand("version" to project.version)
    }

}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    relocate("com.fren_gor.ultimateAdvancementAPI", "me.teakivy.libs.ultimateAdvancementAPI")
    archiveClassifier.set("") // Removes the `-all` suffix
    mergeServiceFiles()

    val customDir = outputDir ?: layout.buildDirectory.dir("libs")
    destinationDirectory.set(file(customDir))
}
