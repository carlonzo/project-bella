plugins {
	id("kotlin-multiplatform")
	id("android-setup")
}

kotlin {
	jvm("desktop")
	android()
	ios()

//	js(IR) {
//		browser()
//	}

	sourceSets {
		named("commonTest") {
			dependencies {
				implementation(Deps.JetBrains.Kotlin.testCommon)
			}
		}

		named("androidTest") {
			dependencies {
				implementation(Deps.JetBrains.Kotlin.testJunit)
			}
		}
		named("desktopTest") {
			dependencies {
				implementation(Deps.JetBrains.Kotlin.testJunit)
			}
		}

//        maybe one day..
//        named("jsTest") {
//            dependencies {
//                implementation(Deps.JetBrains.Kotlin.testJs)
//            }
//        }
	}

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}
}
