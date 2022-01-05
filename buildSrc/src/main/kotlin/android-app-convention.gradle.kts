@file:Suppress("UnstableApiUsage")
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

plugins {
    id("com.android.application")
    id("kotlin-android")
}

configure<BaseAppModuleExtension> {
    compileSdk = AppConfiguration.CompileSdk

    defaultConfig {
        minSdk = AppConfiguration.MinSdk
        targetSdk = AppConfiguration.TargetSdk
        versionCode = AppConfiguration.VersionCode
        versionName = AppConfiguration.VersionName
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
            isShrinkResources = true
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
