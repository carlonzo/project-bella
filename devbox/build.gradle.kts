plugins {
	id("com.android.library")
}

android {
	compileSdk = 32

	defaultConfig {
		minSdk = 21
		targetSdk = 32
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	namespace = "com.projectbella.devbox"
}

dependencies {
	implementation(libs.coroutines.android)
	implementation(libs.ktor.network)
}