package work.racka.thinkrchive.v2.common.features.settings

import kotlinx.coroutines.CoroutineScope
import work.racka.thinkrchive.v2.common.features.settings.container.SettingsContainerHost
import work.racka.thinkrchive.v2.common.features.settings.container.SettingsContainerHostImpl
import work.racka.thinkrchive.v2.common.settings.repository.SettingsRepository

class AppSettings(
    settings: SettingsRepository,
    scope: CoroutineScope
) {
    val host: SettingsContainerHost by lazy {
        SettingsContainerHostImpl(
            settings = settings,
            scope = scope
        )
    }
}