pluginManagement {
    repositories {
        google()  // Menambahkan repository Google
        mavenCentral() // Menambahkan Maven Central
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MVVM Project"
include(":app")
 