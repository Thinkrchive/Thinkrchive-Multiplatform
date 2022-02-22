import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version Versions.kotlin
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = "work.racka.thinkrchive.v2.common.database.android"
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(
            project.rootProject.file("local.properties")
                .reader()
        )
        buildConfigField(
            "String",
            "qonversion_key",
            properties.getProperty("qonversion_key")
        )
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += mutableSetOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "META-INF/licenses/ASM"
            )
            // Fixes conflicts caused by ByteBuddy library used in
            // coroutines-debug and mockito
            pickFirsts += mutableSetOf(
                "win32-x86-64/attach_hotspot_windows.dll",
                "win32-x86/attach_hotspot_windows.dll"
            )
        }
    }
}

dependencies {
    implementation(project(":common:platform"))
    implementation(project(":common:database"))

    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.lifecycleRuntimeKtx)
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    // Testing
    testImplementation(Dependencies.Android.junit)
    testImplementation(Dependencies.Android.testArchCore)
    testImplementation(Dependencies.Android.junitTest)
    testImplementation(Dependencies.Android.testExtJUnitKtx)
    testImplementation(Dependencies.Android.mockitoInline)
    testImplementation(Dependencies.Android.mockitoKotlin)
    testImplementation(Dependencies.Android.robolectric)
    testImplementation(Dependencies.Android.turbine)
    testImplementation(Dependencies.Android.coroutineTest)

    androidTestImplementation(Dependencies.Android.junitTest)
    androidTestImplementation(Dependencies.Android.espressoCore)
    androidTestImplementation(Dependencies.Android.testCoreKtx)
    androidTestImplementation(Dependencies.Android.testArchCore)
    androidTestImplementation(Dependencies.Android.mockitoAndroid)
    androidTestImplementation(Dependencies.Android.mockitoKotlin)
    androidTestImplementation(Dependencies.Android.turbine)

    // Hilt Testing
    // Local Unit Tests
    testImplementation(Dependencies.Android.hiltTest)
    kaptTest(Dependencies.Android.hiltCompiler)
    // Instrumentation Test
    androidTestImplementation(Dependencies.Android.hiltTest)
    kaptAndroidTest(Dependencies.Android.hiltCompiler)

    // Testing Compose
    androidTestImplementation(Dependencies.Android.junitCompose)
    debugImplementation(Dependencies.Android.composeTooling)

    // Compose
    implementation(Dependencies.Android.composeUi)
    implementation(Dependencies.Android.composeAnimation)
    implementation(Dependencies.Android.composeMaterial)
    implementation(Dependencies.Android.composePreview)
    implementation(Dependencies.Android.composeActivity)
    implementation(Dependencies.Android.composeViewModel)
    implementation(Dependencies.Android.composeNavigation)
    implementation(Dependencies.Android.composeMaterialIconsCore)
    implementation(Dependencies.Android.composeMaterialIconsExtended)
    implementation(Dependencies.Android.composeFoundation)
    implementation(Dependencies.Android.composeFoundationLayout)
    implementation(Dependencies.Android.composeConstraintLayout)
    implementation(Dependencies.Android.composeMaterial3)

    // Kotlin Json Serialization
    implementation(Dependencies.Kotlin.kotlinJsonSerialization)

    // Ktor
    implementation(Dependencies.Ktor.ktorCore)
    implementation(Dependencies.Ktor.ktorAndroidEngine)
    implementation(Dependencies.Ktor.ktorOkHttpEngine)
    implementation(Dependencies.Ktor.ktorSerialization)
    implementation(Dependencies.Ktor.ktorLogging)

    // Timber
    implementation(Dependencies.Android.timber)

    // Hilt
    implementation(Dependencies.Android.hilt)
    implementation(Dependencies.Android.hiltNavigationCompose)
    kapt(Dependencies.Android.hiltCompiler)

    // Coil Image loader
    implementation(Dependencies.Android.coilImage)

    // Accompanist
    implementation(Dependencies.Android.accompanistInsets)
    implementation(Dependencies.Android.accompanistNavigationAnimations)

    // Room database
    implementation(Dependencies.Android.roomRuntime)
    implementation(Dependencies.Android.roomKtx)
    kapt(Dependencies.Android.roomCompiler)

    // Room test
    testImplementation(Dependencies.Android.roomTest)

    // Preferences DataStore
    implementation(Dependencies.Android.prefDataStore)

    // Splash Screen
    implementation(Dependencies.Android.splashScreenCore)

    // Billing
    implementation(Dependencies.Android.googleBilling)
    implementation(Dependencies.Android.qonversion)

}