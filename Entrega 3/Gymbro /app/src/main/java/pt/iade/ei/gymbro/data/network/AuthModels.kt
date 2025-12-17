package pt.iade.ei.gymbro.data.network

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    @SerializedName("token") val token: String?,


    @SerializedName("id") val id: Int?,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("user") val user: UserLoginDto?
)

data class UserLoginDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("email") val email: String?
)


data class RegisterRequest(

    @SerializedName("nomeCompleto")
    val nomeCompleto: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)

data class RegisterResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("email") val email: String?,
    @SerializedName("token") val token: String?
)