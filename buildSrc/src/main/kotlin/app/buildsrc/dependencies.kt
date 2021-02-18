package app.buildsrc

object ProjectProperties {
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"

    object App {
        const val id = "app.compose"

        object Version {
            const val code = 1
            const val name = "1.0"
        }
    }

    object SDK {
        object Versions {
            const val minimum = 21
            const val target = 30
            const val compile = target
            const val buildTools = "30.0.3"
        }
    }
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
}

object Dependencies {
    object GradlePlugins {
        const val android = "com.android.tools.build:gradle:7.0.0-alpha07"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30"
    }

    object Junit {
        const val junit = "junit:junit:4.13.2"
    }

    object Activity {
        const val activity = "androidx.activity:activity-compose:1.3.0-alpha02"
    }

    object Material {
        const val material = "com.google.android.material:material:1.3.0"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.3.2"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"

        object Compose {
            const val version = "1.0.0-alpha12"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIcons = "androidx.compose.material:material-icons-extended:$version"
        }

        object Lifecycle {
            private const val version = "2.3.0"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha01"
        }

        object Navigation {
            private const val version = "1.0.0-alpha07"
            const val composeNavigation = "androidx.navigation:navigation-compose:$version"
        }

        object Test {
            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
            const val extJunit = "androidx.test.ext:junit-ktx:1.1.2"
        }
    }
}