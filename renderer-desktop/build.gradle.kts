
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "0.4.0-build183"
}


kotlin {
    jvm(){
//        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(project(":parser"))
                implementation(compose.desktop.currentOs)
            }
        }
    }
}
compose.desktop {
    application {
        mainClass = "MainKt"
    }
}