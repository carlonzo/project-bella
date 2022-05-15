plugins {
	kotlin("multiplatform")
	id("com.android.library")
	id("org.jetbrains.compose") version "0.0.0-master-dev673"
}

version = "1.0"

repositories {
	maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
	jvm("desktop")
	android()

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(project(":parser"))
				implementation(compose.runtime)
				implementation(compose.foundation)
				implementation(compose.material)
				implementation(compose.preview)
			}
		}
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test"))
			}
		}
		val androidMain by getting {
			dependencies {
				implementation("io.coil-kt:coil-compose:2.0.0-rc03")
			}
		}
		val desktopMain by getting {
			dependencies {
				implementation(compose.desktop.common)
			}
		}
	}
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

	sourceSets {
		named("main") {
			manifest.srcFile("src/androidMain/AndroidManifest.xml")
			res.srcDirs("src/androidMain/res")
		}
	}
}