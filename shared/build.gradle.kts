plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.squareup.sqldelight")
}

val sqlDelightVersion = "1.5.5"
val ktorVersion = "2.0.0"
val koin_version= "3.3.3"



kotlin {

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

    }
    
    targets.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().forEach{
        it.binaries.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.Framework>()
            .forEach { lib ->
                lib.isStatic = false
                lib.linkerOpts.add("-lsqlite3")
            }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {

        commonMain.dependencies {
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
                implementation("io.insert-koin:koin-core:$koin_version")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("com.russhwolf:multiplatform-settings-no-arg:1.1.0")

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {

                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

        }
        iosMain.dependencies {

                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                implementation ("io.ktor:ktor-client-ios:$ktorVersion")

        }

    }

}


sqldelight{
    database("CartDatabase"){
        packageName = "com.example.coffeeshop.database"
        sourceFolders = listOf("sqldelight")
        linkSqlite = true
    }

}

android {
    namespace = "com.example.coffeeshop"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
