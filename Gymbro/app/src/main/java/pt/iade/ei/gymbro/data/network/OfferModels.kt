package pt.iade.ei.gymbro.data.network

import com.google.gson.annotations.SerializedName

// --- RESPOSTA DE OFERTA (GET) ---
data class OfferResponse(
    // CORREÇÃO: O JSON traz "ofertaId"
    @SerializedName("ofertaId", alternate = ["id"])
    val id: Int,

    @SerializedName("titulo", alternate = ["title"])
    val title: String?,

    @SerializedName("descricao", alternate = ["description"])
    val description: String?,

    // CORREÇÃO: O JSON traz "nomeCriador"
    @SerializedName("nomeCriador", alternate = ["userName", "userNome", "criadorNome"])
    val userName: String?,

    @SerializedName("localizacaoNome", alternate = ["locationName"])
    val locationName: String?,

    @SerializedName("tipoTreinoNome", alternate = ["workoutTypeName"])
    val workoutTypeName: String?,

    @SerializedName("nivelNome", alternate = ["levelName"])
    val levelName: String?,

    // --- CAMPOS EM FALTA NO BACKEND ---
    // Eles virão a null enquanto o backend não adicionar "diaSemanaNome" e "periodoDiaNome"
    @SerializedName("diaSemanaNome", alternate = ["diaSemana", "weekday"])
    val weekday: String?,

    @SerializedName("periodoDiaNome", alternate = ["periodoDia", "timePeriod"])
    val timePeriod: String?,

    // ID do criador (Continua a faltar no JSON, o botão vai aparecer sempre)
    @SerializedName("criadorId", alternate = ["userId", "usuarioId"])
    val userId: Int?
)

// --- CRIAÇÃO DE OFERTA (POST) ---
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