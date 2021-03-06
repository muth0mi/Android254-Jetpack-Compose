buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(app.buildsrc.Dependencies.GradlePlugins.android)
        classpath(app.buildsrc.Dependencies.GradlePlugins.kotlin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean").configure {
    delete("build")
}