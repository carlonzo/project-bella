plugins {
	id("multiplatform-setup")
	kotlin("plugin.serialization")
}

kotlin {

	sourceSets {

		val commonMain by getting {
			dependencies {
				implementation(Deps.JetBrains.Serialization.json)
			}

		}
	}

}