package pt.iade.ei.gymbro.data.network

import com.google.gson.annotations.SerializedName


data class OfferResponse(
    @SerializedName("ofertaId", alternate = ["id"])
    val id: Int,

    @SerializedName("titulo", alternate = ["title"])
    val title: String?,

    @SerializedName("descricao", alternate = ["description"])
    val description: String?,

    @SerializedName("localizacaoNome", alternate = ["locationName"])
    val locationName: String?,

    @SerializedName("tipoTreinoNome", alternate = ["workoutTypeName"])
    val workoutTypeName: String?,

    @SerializedName("nivelNome", alternate = ["levelName"])
    val levelName: String?,

    @SerializedName("diaSemanaNome", alternate = ["diaSemana", "weekday"])
    val weekday: String?,

    @SerializedName("periodoDiaNome", alternate = ["periodoDia", "timePeriod"])
    val timePeriod: String?,


    @SerializedName("criadorId", alternate = ["userId", "usuarioId", "idCriador", "creatorId"])
    private val _rawUserId: Int?,


    @SerializedName("user", alternate = ["criador", "creator", "usuario"])
    val userObj: OfferUserDto?,


    @SerializedName("nomeCriador", alternate = ["userName", "userNome", "criadorNome"])
    val _rawUserName: String?
) {

    val userId: Int
        get() = _rawUserId ?: userObj?.id ?: 0

    val userName: String?
        get() = _rawUserName ?: userObj?.name
}

data class OfferUserDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name", alternate = ["nome", "username"]) val name: String?
)


data class CreateOfferRequest(
    @SerializedName("titulo") val title: String,
    @SerializedName("descricao") val description: String,
    @SerializedName("data") val date: String = "",
    @SerializedName("duracao") val duration: Double = 60.0,
    @SerializedName("diaSemanaId") val weekdayId: Int,
    @SerializedName("periodoDiaId") val periodId: Int,
    @SerializedName("localizacaoId") val locationId: Int,
    @SerializedName("tipoTreinoId") val workoutTypeId: Int,
    @SerializedName("nivelId") val levelId: Int,
    @SerializedName("userId") val userId: Int
)