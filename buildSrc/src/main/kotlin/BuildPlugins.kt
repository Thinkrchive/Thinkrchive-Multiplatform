object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val composeDesktop by lazy { "org.jetbrains.compose:compose-gradle-plugin:${Versions.composeDesktop}" }
    val daggerHilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}" }
    val sqlDelight by lazy { "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}" }
    val kotlinxSerialization by lazy { "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}" }
}