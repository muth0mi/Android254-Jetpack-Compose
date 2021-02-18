import app.buildsrc.ProjectProperties
import app.buildsrc.Dependencies

plugins {
    id(app.buildsrc.Plugins.androidApplication)
    id(app.buildsrc.Plugins.kotlinAndroid)
}

android {
    compileSdkVersion(ProjectProperties.SDK.Versions.compile)
    buildToolsVersion(ProjectProperties.SDK.Versions.buildTools)
    defaultConfig {
        applicationId = ProjectProperties.App.id
        versionCode = ProjectProperties.App.Version.code
        versionName = ProjectProperties.App.Version.name
        minSdkVersion(ProjectProperties.SDK.Versions.minimum)
        targetSdkVersion(ProjectProperties.SDK.Versions.target)
        testInstrumentationRunner = ProjectProperties.testRunner
    }
    buildTypes {
        getByName("release") { isMinifyEnabled = false }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.version }
}

dependencies {
    implementation(Dependencies.Material.material)

    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.appcompat)

    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.uiTooling)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.materialIcons)
    implementation(Dependencies.AndroidX.Compose.runtimeLivedata)

    implementation(Dependencies.AndroidX.Lifecycle.runtime)
    implementation(Dependencies.AndroidX.Lifecycle.livedata)

    implementation(Dependencies.AndroidX.Navigation.composeNavigation)

    testImplementation(Dependencies.Junit.junit)
    androidTestImplementation(Dependencies.AndroidX.Test.extJunit)
    androidTestImplementation(Dependencies.AndroidX.Test.espressoCore)
}