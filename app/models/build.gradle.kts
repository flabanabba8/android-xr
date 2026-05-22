plugins {
    id("com.android.library")
}

android {
    namespace = "com.xrcaption.models"
    compileSdk = 34

    defaultConfig {
        minSdk = 32
    }

    buildFeatures {
        buildConfig = false
    }
}
