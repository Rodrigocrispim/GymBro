package pt.iade.ei.gymbro.data.network

import com.google.gson.annotations.SerializedName


data class ApplicationResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("status")
    val status: String?,

    @SerializedName("tituloOferta", alternate = ["offerTitle", "titulo"])
    val offerTitle: String?,

    @SerializedName("comentario", alternate = ["comment", "message"])
    val comment: String?,

    @SerializedName("nomeCandidato", alternate = ["candidateName", "candidatoNome", "userName"])
    val candidateName: String?
)


data class CreateApplicationRequest(
    @SerializedName("ofertaId") val ofertaId: Int,
    @SerializedName("comentario") val comentario: String
)


data class UpdateStatusRequest(
    @SerializedName("status") val status: String
)