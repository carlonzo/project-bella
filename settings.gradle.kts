pluginManagement {

    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

}

rootProject.name = "Project Bella"
include(":app")
include(":renderer-android")
include(":parser")
include(":devbox")
include(":renderer-desktop")
