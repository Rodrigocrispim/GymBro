package pt.iade.ei.gymbro.ui.screens.login

import android.util.Base64
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import pt.iade.ei.gymbro.data.network.LoginRequest
import pt.iade.ei.gymbro.data.network.LoginResponse
import pt.iade.ei.gymbro.data.network.RetrofitInstance
import pt.iade.ei.gymbro.ui.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {


    var email by mutableStateOf("nuno@gmail.com")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun login(onSuccess: () -> Unit) {
        isLoading = true
        errorMessage = null

        val request = LoginRequest(email, password)

        RetrofitInstance.api.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                isLoading = false

                if (response.isSuccessful) {
                    val body = response.body()
                    val token = body?.token

                    if (token != null) {

                        var finalId = body.id ?: body.userId ?: body.user?.id


                        if (finalId == null || finalId == 0) {
                            Log.w("LoginVM", "ID não encontrado no Body. A tentar ler do Token...")
                            finalId = getUserIdFromToken(token)
                        }


                        val idParaGuardar = finalId ?: 0

                        Log.d("LoginVM", "--- SUCESSO NO LOGIN ---")
                        Log.d("LoginVM", "Token: ${token.take(15)}...") // Mostra só o início
                        Log.d("LoginVM", "ID Capturado: $idParaGuardar")


                        SessionManager.saveSession(token = token, userId = idParaGuardar)

                        onSuccess()

                    } else {
                        errorMessage = "Erro: O servidor não enviou o Token."
                    }
                } else {
                    errorMessage = "Login falhou: ${response.code()}"

                    val errorBody = response.errorBody()?.string()
                    Log.e("LoginVM", "Login falhou: $errorBody")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                isLoading = false
                errorMessage = "Erro de rede: ${t.message}"
                Log.e("LoginVM", "Erro de rede: ${t.message}")
            }
        })
    }



    private fun getUserIdFromToken(token: String): Int? {
        try {
            val payloadJson = decodeTokenPayload(token) ?: return null
            val jsonObject = JSONObject(payloadJson)


            return when {
                jsonObject.has("id") -> jsonObject.getInt("id")
                jsonObject.has("userId") -> jsonObject.getInt("userId")
                jsonObject.has("user_id") -> jsonObject.getInt("user_id")
                jsonObject.has("sub") -> jsonObject.getString("sub").toIntOrNull()
                else -> null
            }
        } catch (e: Exception) {
            Log.e("LoginVM", "Erro ao fazer parse do token: ${e.message}")
            return null
        }
    }

    private fun decodeTokenPayload(token: String): String? {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) return null

            String(Base64.decode(parts[1], Base64.URL_SAFE))
        } catch (e: Exception) {
            null
        }
    }
}