package work.racka.thinkrchive.v2.common.features.settings.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinApplication
import org.koin.dsl.module
import work.racka.thinkrchive.v2.common.features.settings.AppSettings

object UserPreferences {

    fun KoinApplication.appSettingsModules() =
        this.apply {
            modules(
                commonModule(),
                Platform.platformSettingsModule()
            )
        }

    private fun commonModule() = module {
        factory {
            CoroutineScope(Dispatchers.Default + SupervisorJob())
        }

        factory {
            AppSettings(
                settings = get(),
                scope = get()
            )
        }
    }
}