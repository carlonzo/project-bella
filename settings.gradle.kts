dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			from(files("versions.toml"))
		}
	}
}

pluginManagement {
	repositories {
		google()
		gradlePluginPortal()
		mavenCentral()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}
}

rootProject.name = "Project Bella"

include(":demo-android")
include(":demo-desktop")
include(":parser")
include(":devbox")
include(":renderer")
