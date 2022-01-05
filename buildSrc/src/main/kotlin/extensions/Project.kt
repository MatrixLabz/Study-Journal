/* Android with clean and multi module architecture */
@file:Suppress("UnstableApiUsage")

package extensions

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Specifies defaults for application id and  test instrumentation runner.
 */
fun Project.configuration(applicationId: String, testInstrumentationRunner: String) =
    configure<BaseAppModuleExtension> {
        defaultConfig {
            this.applicationId = applicationId
            this.testInstrumentationRunner = testInstrumentationRunner
        }
    }

/**
 * Specifies defaults for test instrumentation runner.
 */
fun Project.configuration(testInstrumentationRunner: String) =
    configure<LibraryExtension> {
        defaultConfig {
            this.testInstrumentationRunner = testInstrumentationRunner
        }
    }

/**
 * Specifies defaults for test instrumentation runner and adds fields for
 * the generated BuildConfig.
 */
fun Project.configuration(testInstrumentationRunner: String, configFields: List<ConfigField>) =
    configure<LibraryExtension> {
        defaultConfig {
            this.testInstrumentationRunner = testInstrumentationRunner
        }
        buildTypes {
            debug {
                configFields.forEach { buildConfigField(it.type, it.name, it.value) }
            }
            release {
                configFields.forEach { buildConfigField(it.type, it.name, it.value) }
            }
        }
    }
