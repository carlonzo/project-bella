

plugins {
	id("org.jetbrains.compose") version "1.2.0-alpha01-dev683"
	alias(libs.plugins.android.application) apply false
	alias(libs.plugins.android.library) apply false
	alias(libs.plugins.kotlin.gradle) apply false
}

allprojects {
	repositories {
		google()
		mavenCentral()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}
}