/* Android with clean and multi module architecture */
package extensions

import org.gradle.kotlin.dsl.DependencyHandlerScope

/**
 * Adds a list of dependencies to the 'implementation' configuration.
 */
fun DependencyHandlerScope.implementation(dependency: List<Pair<String, String>>) {
    dependency.forEach { add(it.first, it.second) }
}
