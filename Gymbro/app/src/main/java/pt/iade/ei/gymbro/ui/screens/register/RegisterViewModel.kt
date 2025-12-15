package pt.iade.ei.gymbro.ui.screens.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pt.iade.ei.gymbro.data.network.RegisterRequest
import pt.iade.ei.gymbro.data.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    // Variáveis do Ecrã (O que o utilizador escreve)
    var username by mutableStateOf("") // Isto vai ser o "nomeCompleto"
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun register(onSuccess: () -> Unit) {
        isLoading = true
        errorMessage = null

        // Mapeamento para o formato do Backend
        val request = RegisterRequest(
            nome = username, // <--- AGORA SIM! O valor da tua caixa de texto 'username' vai para o campo 'nome'
            email = email,
            password = password
        )

        Log.d("RegisterVM", "A enviar: $request")

        RetrofitInstance.api.register(request).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                isLoading = false
                if (response.isSuccessful) {
                    Log.d("RegisterVM", "Registo com sucesso! Código: ${response.code()}")
                    onSuccess()
                } else {
                    // Se der erro, vamos ver o porquê
                    val errorBody = response.errorBody()?.string()
                    errorMessage = "Falha no registo (${response.code()})"
                    Log.e("RegisterVM", "ERRO API: $errorBody")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                isLoading = false
                errorMessage = "Falha de rede: ${t.message}"
                Log.e("RegisterVM", "Erro Rede: ${t.message}")
            }
        })
    }
}