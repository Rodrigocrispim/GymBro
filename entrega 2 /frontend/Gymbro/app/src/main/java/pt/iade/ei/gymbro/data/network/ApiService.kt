package pt.iade.ei.gymbro.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val userId: Long, val userName: String)


data class CreateOfferRequest(
    val title: String,
    val location: String,
    val date: String,
    val time: String,
    val workoutType: String,
    val levelRequired: String,
    val maxPeople: Int,
    val description: String,
    val createdById: Long
)
data class CreateOfferResponse(val offerId: Long, val title: String, val message: String)



data class OfferResponse(
    val ofertaId: Int,
    val titulo: String,
    val descricao: String,
    val nomeCriador: String,
    val localizacaoNome: String,
    val nivelNome: String,
    val tipoTreinoNome: String
)



interface ApiService {

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>


    @POST("api/ofertas")
    suspend fun createOffer(@Body request: CreateOfferRequest): Response<CreateOfferResponse>


    @GET("api/ofertas")
    suspend fun getOffers(): Response<List<OfferResponse>>
}