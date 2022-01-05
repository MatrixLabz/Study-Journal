import extensions.configuration
import extensions.implementation

plugins {
    `android-library-convention`
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

configuration(testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner")

dependencies {
    implementation(Libraries.navigation())
    implementation(Libraries.daggerHilt())
    implementation(Libraries.coroutines())
    implementation(Libraries.lifecycle)
    implementation(Libraries.viewBindingDelegate)

    implementation(project(":common:core"))
    implementation(project(":common:core-ui"))

    testImplementation(Libraries.jUnit)
    androidTestImplementation(Libraries.androidXJunit)
    androidTestImplementation(Libraries.espresso)
}

kapt {
    correctErrorTypes = true
}