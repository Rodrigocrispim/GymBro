package pt.iade.ei.gymbro.ui.screens.home

// --- O IMPORT QUE FALTAVA ---
import pt.iade.ei.gymbro.data.network.OfferResponse

data class HomeUiState(
    val isLoading: Boolean = false,
    val offers: List<OfferResponse> = emptyList(),
    val error: String? = null
)