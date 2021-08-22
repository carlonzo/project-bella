plugins {
	kotlin("multiplatform")
	id("com.android.library")
	kotlin("plugin.serialization")
}

kotlin {
	android()
	jvm()

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(Deps.JetBrains.Serialization.json)
			}

		}
	}

}

android {
	compileSdk = 30

	defaultConfig {
		minSdk = 21
		targetSdk = 30
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	sourceSets["main"].manifest.srcFile("src/androidMain/kotlin/AndroidManifest.xml")
}
