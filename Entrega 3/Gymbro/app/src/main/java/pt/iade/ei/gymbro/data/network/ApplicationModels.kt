package pt.iade.ei.gymbro.data.network

import com.google.gson.annotations.SerializedName

// Modelo de resposta (já tinhas este)
data class ApplicationResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("status") val status: String?,
    @SerializedName("tituloOferta") val offerTitle: String?,
    @SerializedName("comentario") val comment: String?,
    @SerializedName("nomeCandidato")   val candidateName: String?,
)

// ⚠️ NOVO: Modelo para CRIAR uma candidatura (POST)
data class CreateApplicationRequest(
    @SerializedName("ofertaId") val ofertaId: Int,
    @SerializedName("comentario") val comentario: String

)
data class UpdateStatusRequest(
    @SerializedName("status") val status: String
)

