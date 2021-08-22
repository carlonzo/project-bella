plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":parser"))
            }
        }

        named("androidMain") {
            dependencies {
                implementation(Deps.coil)
            }
        }
    }
}
