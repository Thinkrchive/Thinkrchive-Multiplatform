package work.racka.thinkrchive.v2.common.settings.repository

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class MultiplatformSettingsImpl(
    private val settings: Settings
) : MultiplatformSettings {

    private object Keys {
        const val THEME_OPTION = "THEME_OPTION"
        const val SORT_OPTION = "SORT_OPTION"
    }

    private val _sortFlow: MutableStateFlow<Int> = MutableStateFlow(readSortSettings())
    private val _themeFlow: MutableStateFlow<Int> = MutableStateFlow(readThemeSettings())

    override val themeFlow: Flow<Int>
        get() = _themeFlow

    override val sortFlow: Flow<Int>
        get() = _sortFlow

    override fun saveThemeSettings(value: Int) {
        settings.putInt(
            key = Keys.THEME_OPTION,
            value = value
        )
        _themeFlow.update { readThemeSettings() }
    }

    override fun saveSortSettings(value: Int) {
        settings.putInt(
            key = Keys.SORT_OPTION,
            value = value
        )
        _sortFlow.update { readSortSettings() }
    }

    private fun readThemeSettings(): Int =
        settings.getInt(
            key = Keys.THEME_OPTION,
            defaultValue = -1
        )

    private fun readSortSettings(): Int =
        settings.getInt(
            key = Keys.SORT_OPTION,
            defaultValue = 0
        )
}