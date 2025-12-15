package pt.iade.ei.gymbro.ui.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pt.iade.ei.gymbro.data.network.ProfileResponse
import pt.iade.ei.gymbro.data.network.UpdateProfileRequest
import pt.iade.ei.gymbro.data.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var locationId by mutableStateOf<Int?>(null)
    var levelId by mutableStateOf<Int?>(null)
    var goalId by mutableStateOf<Int?>(null)
    var bio by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun loadProfile() {
        isLoading = true
        errorMessage = null

        RetrofitInstance.api.getProfile().enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                isLoading = false
                if (response.isSuccessful) {
                    val p = response.body()
                    fullName = p?.nomeCompleto.orEmpty()
                    email = p?.email.orEmpty()
                    bio = p?.biografia.orEmpty()
                    locationId = p?.localizacaoId
                    levelId = p?.nivelId
                    goalId = p?.objetivoId
                } else {
                    errorMessage = "Erro ao carregar perfil: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                isLoading = false
                errorMessage = "Erro de rede: ${t.message}"
            }
        })
    }

    fun saveProfile(onSuccess: () -> Unit) {
        isLoading = true
        errorMessage = null

        val body = UpdateProfileRequest(
            nomeCompleto = fullName,
            biografia = bio,
            urlFotoPerfil = null,
            localizacaoId = locationId,
            nivelId = levelId,
            objetivoId = goalId
        )

        RetrofitInstance.api.updateProfile(body).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                isLoading = false
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    errorMessage = "Erro ao guardar perfil: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                isLoading = false
                errorMessage = "Erro de rede: ${t.message}"
            }
        })
    }
}
