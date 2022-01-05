import extensions.configuration
import extensions.implementation

plugins {
    `android-app-convention`
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

configuration(
    applicationId = "com.matrix.studyjournal",
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
)

dependencies {
    implementation(Libraries.room())
    implementation(Libraries.navigation())
    implementation(Libraries.daggerHilt())
    implementation(Libraries.preferences)

    implementation(project(":common:core"))
    implementation(project(":common:core-ui"))
    implementation(project(":features:main"))
    implementation(project(":features:timer"))
    implementation(project(":features:study-note"))
}

kapt {
    correctErrorTypes = true
}