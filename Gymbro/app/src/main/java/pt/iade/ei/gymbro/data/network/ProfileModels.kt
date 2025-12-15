package pt.iade.ei.gymbro.data.network

data class ProfileResponse(
    val id: Int,
    val nomeCompleto: String?,
    val email: String?,
    val biografia: String?,
    val urlFotoPerfil: String?,
    val localizacaoId: Int?,
    val nivelId: Int?,
    val objetivoId: Int?
)

data class UpdateProfileRequest(
    val nomeCompleto: String?,
    val biografia: String?,
    val urlFotoPerfil: String?,
    val localizacaoId: Int?,
    val nivelId: Int?,
    val objetivoId: Int?
)

// Se usarem objetivos de fitness
data class GoalItem(
    val id: Int,
    val nome: String
)
