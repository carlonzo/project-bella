plugins {
	kotlin("multiplatform")
	kotlin("plugin.serialization") version "1.6.20"
}

kotlin {
	jvm()

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(libs.kotlinx.serialization)
			}
		}
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test"))
			}
		}

		val jvmMain by getting
		val jvmTest by getting
	}
}