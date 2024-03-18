import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    kotlin("native.cocoapods")
    // required for now to include resources from Chat SDK
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        name = "ComposeApp"
        version = "1.0"
        homepage = "https://botstacks.ai"
        summary = "Some cool story"
        ios.deploymentTarget = "15.0"

        podfile = file("../iosApp/Podfile")

        framework {
            baseName = "ComposeApp"
            isStatic = true
        }

        pod("Giphy") {
            moduleName = "GiphyUISDK"
            version = "2.2.8"
            extraOpts += listOf("-compiler-option", "-fmodules")
        }

        pod("GoogleMaps") {
            version = "8.4.0"
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
    }
    
    sourceSets {
        androidMain {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.androidx.activity.compose)
            }
        }

        commonMain {
            dependencies {
                implementation("ai.botstacks:chat-sdk:1.0.3")
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.ui)
//                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.voyager.navigator)
                implementation(libs.voyager.navigator.tabs)
                implementation(libs.voyager.transitions)

                implementation(libs.webview)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        iosMain {
            dependsOn(commonMain.get())
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                // required for now to include resources from Chat SDK
                implementation(libs.moko.resources)
            }
        }
    }
}

android {
    namespace = "sample"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "ai.botstacks.sample"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

