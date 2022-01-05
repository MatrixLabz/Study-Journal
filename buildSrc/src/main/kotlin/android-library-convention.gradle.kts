@file:Suppress("UnstableApiUsage")
import com.android.build.gradle.LibraryExtension

plugins {
    id("com.android.library")
    id("kotlin-android")
}

configure<LibraryExtension> {
    compileSdk = AppConfiguration.CompileSdk

    defaultConfig {
        minSdk = AppConfiguration.MinSdk
        targetSdk = AppConfiguration.TargetSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = AppConfiguration.JvmTarget
    }

    buildFeatures {
        viewBinding = true
    }

    dependencies {
        implementation(Libraries.coreKtx)
        implementation(Libraries.appCompat)
        implementation(Libraries.material)
    }
}