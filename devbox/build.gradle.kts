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
}

dependencies {
	implementation(libs.coroutines.android)
	implementation(libs.ktor.network)
}