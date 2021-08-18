
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}


kotlin {
    jvm(){
        withJava()
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