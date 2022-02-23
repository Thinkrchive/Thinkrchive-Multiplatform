package work.racka.thinkrchive.v2.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import work.racka.thinkrchive.v2.android.ui.main.viewModel.DonateViewModel
import work.racka.thinkrchive.v2.android.ui.main.viewModel.ThinkpadDetailsViewModel
import work.racka.thinkrchive.v2.android.ui.main.viewModel.ThinkpadListViewModel
import work.racka.thinkrchive.v2.android.ui.main.viewModel.ThinkpadSettingsViewModel

object ViewModelModule {
    val module = module {
        viewModel {
            DonateViewModel(
                billingRepository = get()
            )
        }

        viewModel {
            ThinkpadDetailsViewModel(
                thinkpadRepository = get()
            )
        }

        viewModel {
            ThinkpadListViewModel(
                thinkpadRepository = get(),
                prefDataStore = get()
            )
        }

        viewModel {
            ThinkpadSettingsViewModel(
                prefDataStore = get()
            )
        }
    }
}