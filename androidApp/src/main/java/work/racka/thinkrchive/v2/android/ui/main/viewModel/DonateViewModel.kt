package work.racka.thinkrchive.v2.android.ui.main.viewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.SkuDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import work.racka.thinkrchive.v2.android.repository.BillingRepository
import work.racka.thinkrchive.v2.android.ui.main.screenStates.DonateScreenState

class DonateViewModel(
    private val billingRepository: BillingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DonateScreenState>(
        value = DonateScreenState.DefaultState
    )
    val uiState: StateFlow<DonateScreenState>
        get() = _uiState

    init {
        initialize()
        Timber.i("DonateViewModel created")
    }

    private fun initialize() {
        viewModelScope.launch {
            billingRepository.billingResponse.collect { billingResponse ->
                Timber.i("Response: $billingResponse")
                if (billingResponse == BillingClient.BillingResponseCode.OK) {
                    val skuDetailsList = billingRepository.querySkuDetails().skuDetailsList
                    skuDetailsList?.let {
                        _uiState.value = DonateScreenState.Donate(it)
                    }
                }
            }
        }
        viewModelScope.launch {
            billingRepository.purchasesState.collect {
                Timber.i("Purchase query collected: $it")
                billingRepository.consumePurchase()
            }
        }
    }

    fun launchPurchaseScreen(activity: Activity, sku: SkuDetails) {
        billingRepository.launchPurchaseScreen(activity, sku)
    }
}