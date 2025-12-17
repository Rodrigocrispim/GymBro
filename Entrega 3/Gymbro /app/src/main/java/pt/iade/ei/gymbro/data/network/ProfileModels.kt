package pt.iade.ei.gymbro.data.network

import com.google.gson.annotations.SerializedName


data class ProfileResponse(
    @SerializedName("id", alternate = ["utilizador_id", "userId"])
    val id: Int,

    @SerializedName("nome_completo", alternate = ["nomeCompleto", "fullName", "name", "nome"])
    val nomeCompleto: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("biografia", alternate = ["bio", "description"])
    val biografia: String?,

    @SerializedName("foto_perfil", alternate = ["fotoPerfil", "avatar", "photoUrl", "urlFotoPerfil"])
    val urlFotoPerfil: String?,

    @SerializedName("localizacao_id", alternate = ["localizacaoId", "locationId"])
    val localizacaoId: Int?,

    @SerializedName("nivel_id", alternate = ["nivelId", "levelId", "fitnessLevelId"])
    val nivelId: Int?,

    @SerializedName("objetivo_id", alternate = ["objetivoId", "goalId", "objectiveId"])
    val objetivoId: Int?,


    @SerializedName("objetivo", alternate = ["objetivoTexto"])
    val objetivoTexto: String?
)


data class UpdateProfileRequest(
    @SerializedName("nome_completo") val nomeCompleto: String?,
    @SerializedName("biografia") val biografia: String?,


    @SerializedName("objetivo") val objetivoTexto: String? = null,

    @SerializedName("localizacao_id") val localizacaoId: Int? = null,
    @SerializedName("nivel_id") val nivelId: Int? = null,
    @SerializedName("objetivo_id") val objetivoId: Int? = null
)

data class GoalItem(
    @SerializedName("id") val id: Int,
    @SerializedName("nome") val nome: String
)