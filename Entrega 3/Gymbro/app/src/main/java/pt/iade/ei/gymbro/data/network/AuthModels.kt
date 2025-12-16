package pt.iade.ei.gymbro.data.network

import com.google.gson.annotations.SerializedName

// --- LOGIN ---
data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    @SerializedName("token") val token: String?,

    // Campos opcionais (caso o backend decida enviar fora do token um dia)
    @SerializedName("id") val id: Int?,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("user") val user: UserLoginDto?
)

data class UserLoginDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("email") val email: String?
)

// --- REGISTO ---
data class RegisterRequest(
    // ATENÇÃO: Confirma se no RegisterViewModel usas "nome = ..."
    @SerializedName("nome") val nome: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class RegisterResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("email") val email: String?,
    @SerializedName("token") val token: String?
)