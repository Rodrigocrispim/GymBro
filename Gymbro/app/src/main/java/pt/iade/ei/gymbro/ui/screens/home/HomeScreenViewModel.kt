package pt.iade.ei.gymbro.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pt.iade.ei.gymbro.data.network.CreateApplicationRequest
import pt.iade.ei.gymbro.data.network.OfferResponse
import pt.iade.ei.gymbro.data.network.RetrofitInstance
import pt.iade.ei.gymbro.ui.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenViewModel : ViewModel() {

    var offers by mutableStateOf<List<OfferResponse>>(emptyList())
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    init {
        loadOffers()
    }

    fun loadOffers() {
        isLoading = true
        errorMessage = null
        RetrofitInstance.api.getOffers().enqueue(object : Callback<List<OfferResponse>> {
            override fun onResponse(call: Call<List<OfferResponse>>, response: Response<List<OfferResponse>>) {
                isLoading = false
                if (response.isSuccessful) {
                    offers = response.body() ?: emptyList()
                } else {
                    errorMessage = "Erro ao carregar ofertas."
                }
            }
            override fun onFailure(call: Call<List<OfferResponse>>, t: Throwable) {
                isLoading = false
                errorMessage = "Falha de rede."
            }
        })
    }

    // ⚠️ NOVA FUNÇÃO: Enviar Candidatura
    fun applyToOffer(offerId: Int, comment: String, onResult: (Boolean) -> Unit) {
        val request = CreateApplicationRequest(
            ofertaId = offerId,
            comentario = comment
        )

        RetrofitInstance.api.applyToOffer(request)
            .enqueue(object : retrofit2.Callback<Void> {
                override fun onResponse(
                    call: retrofit2.Call<Void>,
                    response: retrofit2.Response<Void>
                ) {
                    onResult(response.isSuccessful)
                }

                override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                    onResult(false)
                }
            })
    }

}