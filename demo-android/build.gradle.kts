plugins {
	id("com.android.application")
	id("kotlin-android")
	id("kotlin-kapt")
	id("org.jetbrains.compose")
}

android {
	compileSdkVersion(30)

	defaultConfig {

		applicationId = "it.carlom.composables"
		minSdkVersion(23)
		targetSdkVersion(30)
		versionCode = 1
		versionName = "1.0"
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

}

dependencies {

	implementation(project(":renderer"))
	implementation(project(":parser"))
	implementation(project(":devbox"))

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")
	implementation("androidx.core:core-ktx:1.6.0")
	implementation(Deps.AndroidX.appCompat)
	implementation(Deps.AndroidX.activityCompose)
	implementation(compose.runtime)
}