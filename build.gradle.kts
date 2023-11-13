plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    id("com.squareup.sqldelight") version "1.5.5" apply false
    kotlin("plugin.serialization") version "1.9.20"
    id("com.google.dagger.hilt.android") version "2.44" apply false
}
