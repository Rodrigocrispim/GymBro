package pt.iade.ei.gymbro.data.repository


import pt.iade.ei.gymbro.data.network.LoginRequest
import pt.iade.ei.gymbro.data.network.LoginResponse
import pt.iade.ei.gymbro.data.network.RetrofitInstance


sealed class LoginResult {
    data class Success(val response: LoginResponse) : LoginResult()
    data class Error(val message: String) : LoginResult()
}


class AuthRepository {


    private val api = RetrofitInstance.api


    suspend fun login(email: String, password: String): LoginResult {
        return try {

            val request = LoginRequest(email, password)


            val response = api.login(request)


            if (response.isSuccessful && response.body() != null) {

                LoginResult.Success(response.body()!!)
            } else {

                LoginResult.Error("Login failed: ${response.message()}")
            }
        } catch (e: Exception) {

            LoginResult.Error("Network error: ${e.message ?: "Unknown error"}")
        }
    }


}