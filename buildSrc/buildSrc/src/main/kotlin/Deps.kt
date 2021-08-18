object Deps {

  object JetBrains {
    object Kotlin {
      private const val VERSION = "1.5.21"
      const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
      const val serializaion = "org.jetbrains.kotlin:kotlin-serialization:$VERSION"
      const val testCommon = "org.jetbrains.kotlin:kotlin-test-common:$VERSION"
      const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"
    }

    object Compose {
      private const val VERSION = "1.0.0-alpha3"
      const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
    }
  }

  object Android {
    object Tools {
      object Build {
        const val gradlePlugin = "com.android.tools.build:gradle:7.1.0-alpha08"
      }
    }
  }

  object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:1.3.0"
    const val activityCompose = "androidx.activity:activity-compose:1.3.0"
  }

  const val coil = "io.coil-kt:coil-compose:1.3.2"
}
