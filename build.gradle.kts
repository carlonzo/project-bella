allprojects {
	repositories {
		google()
		mavenCentral()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}
}

//subprojects {
//
//	tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
//		kotlinOptions {
//			// Treat all Kotlin warnings as errors
//			allWarningsAsErrors = true
//			freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
//			freeCompilerArgs += "-Xopt-in=kotlin.Experimental"
//
//			// Set JVM target to 1.8
//			jvmTarget = "1.8"
//		}
//	}
//}