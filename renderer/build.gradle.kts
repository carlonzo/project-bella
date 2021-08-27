plugins {
	id("multiplatform-compose-setup")
	id("android-setup")
}

kotlin {
	sourceSets {
		named("commonMain") {
			dependencies {
				implementation(project(":parser"))
				implementation(compose.preview)
			}
		}

		named("androidMain") {
			dependencies {
				implementation(Deps.coil)
			}
		}
	}
}
