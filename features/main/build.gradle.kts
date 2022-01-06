import extensions.configuration
import extensions.ConfigField
import extensions.implementation

plugins {
    `android-library-convention`
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
}

configuration(
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner",
    configFields = listOf(
        ConfigField(
            "String",
            "PRIVACY_POLICY_URL",
            "\"SITE_ADDRESS\""
        ),
        ConfigField(
            "String",
            "GOOGLE_PLAY_URL",
            "\"market://details?id=com.matrix.studyjournal\""
        ),
        ConfigField(
            "String",
            "GITHUB_URL",
            "\"https://github.com/it5prasoon/Study-Journal\""
        )
    )
)

dependencies {
    implementation(Libraries.room())
    implementation(Libraries.navigation())
    implementation(Libraries.daggerHilt())
    implementation(Libraries.lifecycle)
    implementation(Libraries.viewBindingDelegate)
    implementation(Libraries.preferences)

    implementation(project(":common:core"))
    implementation(project(":common:core-ui"))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    testImplementation(Libraries.jUnit)
    androidTestImplementation(Libraries.androidXJunit)
    androidTestImplementation(Libraries.espresso)
}

kapt {
    correctErrorTypes = true
}