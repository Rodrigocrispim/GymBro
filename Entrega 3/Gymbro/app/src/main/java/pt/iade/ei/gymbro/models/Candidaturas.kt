package pt.iade.ei.gymbro.models

data class Candidatura(
    val id: Int,
    val nomeCandidato: String,
    val comentario: String,
    val status: String, // "PENDENTE", "ACEITE", etc.
    val tituloOferta: String
)