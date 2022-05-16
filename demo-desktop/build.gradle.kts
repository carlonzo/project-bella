import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
}

kotlin {
	jvm {
		withJava()
	}

	sourceSets {
		named("jvmMain") {
			dependencies {
				implementation(compose.desktop.currentOs)
				implementation(project(":renderer"))
				implementation(project(":parser"))
			}
		}
	}
}

compose.desktop {
	application {
		mainClass = "MainKt"
		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Deb)
			packageName = "ServerDrivenUiDemoDesktop"
			packageVersion = "1.0.0"

			windows {
				menuGroup = "Server Driven UI Demo"
				// see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
				upgradeUuid = "663E77E6-8A91-4B2B-B419-FCF006427196"
			}
		}
	}
}