package pt.iade.ei.gymbro.models

import com.google.gson.annotations.SerializedName

// Este é o formato que vem do GET (Substitui a tua antiga classe Candidatura se quiseres, ou usa esta)
data class ApplicationResponse(
    val id: Int,

    @SerializedName("nomeCandidato") // Garante que o nome bate certo com o JSON
    val nomeCandidato: String,

    val comentario: String,
    val status: String, // "PENDENTE", "ACEITE", "REJEITADO"

    @SerializedName("tituloOferta")
    val tituloOferta: String
)

// Este é o formato que enviamos no PUT para mudar o status
data class UpdateStatusRequest(
    val status: String // Vamos enviar "ACEITE" ou "REJEITADO"
)