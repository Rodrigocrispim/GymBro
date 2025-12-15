package pt.iade.ei.gymbro.ui.screens.applications

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.util.Log
import pt.iade.ei.gymbro.data.network.RetrofitInstance
import pt.iade.ei.gymbro.data.network.ApplicationResponse
import pt.iade.ei.gymbro.data.network.UpdateStatusRequest
import pt.iade.ei.gymbro.ui.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApplicationsViewModel : ViewModel() {

    var candidaturas by mutableStateOf<List<ApplicationResponse>>(emptyList())
    var isLoading by mutableStateOf(true)
    var errorMessage by mutableStateOf<String?>(null)

    fun loadApplications(ofertaId: Int) {
        // VER O TOKEN NO LOGCAT
        Log.d("SESSION", "Token atual: ${SessionManager.token}")

        isLoading = true
        val call = RetrofitInstance.api.getOfferApplications(ofertaId)

        call.enqueue(object : Callback<List<ApplicationResponse>> {
            override fun onResponse(
                call: Call<List<ApplicationResponse>>,
                response: Response<List<ApplicationResponse>>
            ) {
                isLoading = false
                if (response.isSuccessful) {
                    candidaturas = response.body() ?: emptyList()
                } else {
                    errorMessage = "Erro: ${response.code()}"
                }
            }

            override fun onFailure(
                call: Call<List<ApplicationResponse>>,
                t: Throwable
            ) {
                isLoading = false
                errorMessage = t.message
            }
        })
    }

    fun updateStatus(id: Int, newStatus: String, onSuccess: () -> Unit) {
        val request = UpdateStatusRequest(status = newStatus)

        RetrofitInstance.api.updateApplicationStatus(id, request)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        onSuccess()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Tratar erro se quiseres
                }
            })
    }
}
