package pt.iade.ei.gymbro.ui.screens.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pt.iade.ei.gymbro.data.network.OfferResponse
import pt.iade.ei.gymbro.data.repository.OfferListResult
import pt.iade.ei.gymbro.data.repository.OfferRepository


data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val offerList: List<OfferResponse> = emptyList(),
    val error: String? = null
)


class HomeScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val offerRepository = OfferRepository()


    init {
        loadOffers()
    }


    fun loadOffers() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            when (val result = offerRepository.getOffers()) {
                is OfferListResult.Success -> {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            offerList = result.offers
                        )
                    }
                }
                is OfferListResult.Error -> {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}