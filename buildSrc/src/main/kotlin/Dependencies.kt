object Dependencies {

    object Jetbrains {
        val testCommon by lazy { "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}" }
        val testAnnotationsCommon by lazy { "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}" }

    }

    object Kotlin {
        val kotlinJunit by lazy { "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}" }
        val kotlinJsonSerialization by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}" }
        val serializationCore by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinSerialization}" }

        object Coroutines {
            private const val VERSION = "1.6.0"
            val core by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION" }
            val android by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION" }
            val swing by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-swing:$VERSION" }
            val test by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:$VERSION" }
        }
    }

    object Koin {
        val core by lazy { "io.insert-koin:koin-core:${Versions.koin}" }
        val test by lazy { "io.insert-koin:koin-test:${Versions.koin}" }
        val testJUnit4 by lazy { "io.insert-koin:koin-test-junit4:${Versions.koin}" }
        val android by lazy { "io.insert-koin:koin-android:${Versions.koin}" }
        val compose by lazy { "io.insert-koin:koin-androidx-compose:${Versions.koin}" }
    }

    object Ktor {
        val ktorCore by lazy { "io.ktor:ktor-client-core:${Versions.ktor}" }
        val ktorAndroidEngine by lazy { "io.ktor:ktor-client-android:${Versions.ktor}" }
        val ktorOkHttpEngine by lazy { "io.ktor:ktor-client-okhttp:${Versions.ktor}" }
        val ktorJavaEngine by lazy { "io.ktor:ktor-client-java:${Versions.ktor}" }
        val ktorSerialization by lazy { "io.ktor:ktor-client-serialization:${Versions.ktor}" }
        val ktorLogging by lazy { "io.ktor:ktor-client-logging:${Versions.ktor}" }
        val clientJson by lazy { "io.ktor:ktor-client-json:${Versions.ktor}" }
        val json by lazy { "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}" }
        val contentNegotiation by lazy { "io.ktor:ktor-client-content-negotiation:${Versions.ktor}" }
    }

    object OrbitMVI {
        val core by lazy { "org.orbit-mvi:orbit-core:${Versions.orbitMVI}" }
        val androidViewModel by lazy { "org.orbit-mvi:orbit-viewmodel:${Versions.orbitMVI}" }
        val test by lazy { "org.orbit-mvi:orbit-test:${Versions.orbitMVI}" }
    }

    object ArkIvanov {
        object MVIKotlin {
            val rx by lazy { "com.arkivanov.mvikotlin:rx:${Versions.mviKotlin}" }
            val mviKotlin by lazy { "com.arkivanov.mvikotlin:mvikotlin:${Versions.mviKotlin}" }
            val mviKotlinMain by lazy { "com.arkivanov.mvikotlin:mvikotlin-main:${Versions.mviKotlin}" }
            val mviKotlinLogging by lazy { "com.arkivanov.mvikotlin:mvikotlin-logging:${Versions.mviKotlin}" }
            val mviKotlinTimeTravel by lazy { "com.arkivanov.mvikotlin:mvikotlin-timetravel:${Versions.mviKotlin}" }
            val mviKotlinExtensionsCoroutines by lazy {
                "com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:${Versions.mviKotlin}"
            }
        }

        object Decompose {
            private const val VERSION = "0.5.2"
            val decompose by lazy { "com.arkivanov.decompose:decompose:$VERSION" }
            val extensionsCompose by lazy {
                "com.arkivanov.decompose:extensions-compose-jetbrains:$VERSION"
            }
        }
    }

    object RusshWolf {
        object MultiplatformSettings {
            val core by lazy { "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}" }
            val noArg by lazy { "com.russhwolf:multiplatform-settings-no-arg:${Versions.multiplatformSettings}" }
            val coroutines by lazy { "com.russhwolf:multiplatform-settings-coroutines:${Versions.multiplatformSettings}" }
            val test by lazy { "com.russhwolf:multiplatform-settings-test:${Versions.multiplatformSettings}" }
        }
    }

    object Revenuecat {
        val android by lazy { "com.revenuecat.purchases:purchases:${Versions.revenuecat}" }
    }

    object Squareup {
        object SQLDelight {
            val runtime by lazy { "com.squareup.sqldelight:runtime:${Versions.sqlDelight}" }
            val coroutineExtensions by lazy { "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}" }
            val androidDriver by lazy { "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}" }
            val sqliteDriver by lazy { "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}" }
            val jsDriver by lazy { "com.squareup.sqldelight:sqljs-driver:${Versions.sqlDelight}" }
        }
    }

    object Mockk {
        val core by lazy { "io.mockk:mockk:${Versions.mockk}" }
        val android by lazy { "io.mockk:mockk-android:${Versions.mockk}" }
        val jvm by lazy { "io.mockk:mockk-agent-jvm:${Versions.mockk}" }
        val commonMultiplatform by lazy { "io.mockk:mockk-common:${Versions.mockk}" }
    }

    object Log {
        val slf4j by lazy { "org.slf4j:slf4j-simple:${Versions.slf4j}" }
        val logback by lazy { "ch.qos.logback:logback-classic:${Versions.logback}" }
        val kermit by lazy { "co.touchlab:kermit:${Versions.kermit}" }
    }

    object Android {

        // Essentials
        val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
        val material by lazy { "com.google.android.material:material:${Versions.material}" }

        // Lifecycle components
        val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}" }

        // Testing
        val junit by lazy { "junit:junit:${Versions.junit}" }
        val junitTest by lazy { "androidx.test.ext:junit:${Versions.junitTest}" }
        val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoTest}" }
        val testCoreKtx by lazy { "androidx.test:core-ktx:${Versions.testCore}" }
        val testArchCore by lazy { "androidx.arch.core:core-testing:${Versions.testArchCore}" }
        val testExtJUnitKtx by lazy { "androidx.test.ext:junit-ktx:${Versions.testExtJUnit}" }
        val mockitoInline by lazy { "org.mockito:mockito-inline:${Versions.mockito}" }
        val mockitoAndroid by lazy { "org.mockito:mockito-android:${Versions.mockito}" }
        val mockitoKotlin by lazy { "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}" }
        val robolectric by lazy { "org.robolectric:robolectric:${Versions.robolectric}" }
        val turbine by lazy { "app.cash.turbine:turbine:${Versions.turbine}" }
        val coroutineTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineTest}" }

        // Compose Testing
        val junitCompose by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose}" }
        val composeTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }

        // Compose
        val composeUi by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
        val composeMaterial by lazy { "androidx.compose.material:material:${Versions.compose}" }
        val composeAnimation by lazy { "androidx.compose.animation:animation:${Versions.compose}" }
        val composePreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }
        val composeActivity by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
        val composeViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}" }
        val composeNavigation by lazy { "androidx.navigation:navigation-compose:${Versions.composeNavigation}" }
        val composeMaterialIconsCore by lazy { "androidx.compose.material:material-icons-core:${Versions.compose}" }
        val composeMaterialIconsExtended by lazy { "androidx.compose.material:material-icons-extended:${Versions.compose}" }
        val composeFoundation by lazy { "androidx.compose.foundation:foundation:${Versions.compose}" }
        val composeFoundationLayout by lazy { "androidx.compose.foundation:foundation-layout:${Versions.compose}" }
        val composeConstraintLayout by lazy { "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}" }
        val composeMaterial3 by lazy { "androidx.compose.material3:material3:${Versions.composeMaterial3}" }

        // Timber Logging
        val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }

        // Dagger Hilt
        val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
        val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }

        // Hilt testing
        val hiltTest by lazy { "com.google.dagger:hilt-android-testing:${Versions.hilt}" }

        // Coil image loader
        val coilImage by lazy { "io.coil-kt:coil-compose:${Versions.coilImage}" }

        // Accompanist
        object Accompanist {
            val navigationAnimations by lazy { "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}" }
            val swipeRefresh by lazy { "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}" }
            val systemUiController by lazy { "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}" }
        }

        // Preferences DataStore
        val prefDataStore by lazy { "androidx.datastore:datastore-preferences:${Versions.dataStore}" }

        // Splash Screen
        val splashScreenCore by lazy { "androidx.core:core-splashscreen:${Versions.splashScreen}" }

        // Lottie
        val lottie by lazy { "com.airbnb.android:lottie-compose:${Versions.lottie}" }

        // Billing
        val googleBilling by lazy { "com.android.billingclient:billing-ktx:${Versions.gBilling}" }
        val qonversion by lazy { "io.qonversion.android.sdk:sdk:${Versions.qonversion}" }

        // Glance AppWidget - Early Snapshot
        val glanceAppWidget by lazy { "androidx.glance:glance-appwidget:${Versions.glanceAppWidget}" }

    }

}