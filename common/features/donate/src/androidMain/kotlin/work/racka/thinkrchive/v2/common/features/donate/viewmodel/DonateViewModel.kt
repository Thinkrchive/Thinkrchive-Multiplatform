package work.racka.thinkrchive.v2.common.features.donate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import work.racka.thinkrchive.v2.common.billing.api.BillingApi
import work.racka.thinkrchive.v2.common.features.donate.container.DonateContainerHost
import work.racka.thinkrchive.v2.common.features.donate.container.DonateContainerHostImpl

actual class DonateViewModel(
    api: BillingApi
) : ViewModel() {
    val host: DonateContainerHost = DonateContainerHostImpl(
        api = api,
        scope = viewModelScope
    )
}