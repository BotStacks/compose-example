buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        // Add the dependency for the Google services Gradle plugin
        classpath(libs.gradlePlugin.android)
        classpath(libs.gradlePlugin.compose)
        classpath(libs.gradlePlugin.kotlin)
        classpath(libs.google.services)
    }
}
plugins {
    alias(libs.plugins.ksp).apply(false)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}