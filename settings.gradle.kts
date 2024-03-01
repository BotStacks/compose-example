import dev.aga.gradle.versioncatalogs.Generator.generate
import dev.aga.gradle.versioncatalogs.GeneratorConfig

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        generate("firebaseLibs") {
            from(toml("firebase-bom"))
            aliasPrefixGenerator = GeneratorConfig.NO_PREFIX
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.4.0")
    id("dev.aga.gradle.version-catalog-generator") version("1.1.0")
}

rootProject.name = "botstacks-sample"

include("app")