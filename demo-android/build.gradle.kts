plugins {
	id("com.android.application")
	kotlin("android")
}

android {
	compileSdk = 32
	defaultConfig {
		applicationId = "com.projectbella.demo"
		minSdk = 21
		targetSdk = 32
		versionCode = 1
		versionName = "1.0"
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
		}
	}

	buildFeatures.compose = true

	composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.compose.android.get()
	}

	namespace = "com.projectbella.demo"
}

dependencies {
	implementation(project(":parser"))
	implementation(project(":renderer"))
	implementation(project(":devbox"))

	implementation("com.google.android.material:material:1.4.0")
	implementation("androidx.appcompat:appcompat:1.3.1")
	implementation("androidx.constraintlayout:constraintlayout:2.1.0")
	implementation(libs.coroutines.android)
	implementation("com.squareup.okio:okio:3.1.0")

	// compose
	implementation(libs.activity.compose)
	implementation(libs.compose.runtime)
	implementation(libs.compose.ui)
	implementation(libs.compose.ui.tooling.preview)
	debugImplementation(libs.compose.ui.tooling)
}