package pt.iade.ei.gymbro.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // --- 1. AUTENTICAÇÃO ---
    @POST("api/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("api/auth/register")
    fun register(@Body request: RegisterRequest): Call<Void>


    // --- 2. OFERTAS (Home & Create) ---
    @GET("api/ofertas")
    fun getOffers(): Call<List<OfferResponse>>

    @POST("api/ofertas")
    fun createOffer(@Body request: CreateOfferRequest): Call<ResponseBody>


    // --- 3. CANDIDATURAS (Gerir e Criar) ---

    @POST("api/candidaturas")
    fun applyToOffer(@Body request: CreateApplicationRequest): Call<Void>

    @GET("api/candidaturas/usuario/{usuarioId}")
    fun getUserApplications(
        @Path("usuarioId") userId: Int
    ): Call<List<ApplicationResponse>>

    // aqui o path fica exatamente /api/ofertas/{ofertaId}/candidaturas
    @GET("api/ofertas/{ofertaId}/candidaturas")
    fun getOfferApplications(
        @Path("ofertaId") ofertaId: Int
    ): Call<List<ApplicationResponse>>

    @PUT("api/candidaturas/{id}/status")
    fun updateApplicationStatus(
        @Path("id") id: Int,
        @Body request: UpdateStatusRequest
    ): Call<Void>


    // --- 4. DROPDOWNS (Auxiliares) ---

    @GET("api/localizacoes")
    fun getLocations(): Call<List<LocationItem>>

    @GET("api/tipos-treino")
    fun getWorkoutTypes(): Call<List<WorkoutTypeItem>>

    @GET("api/niveis-treino")
    fun getLevels(): Call<List<LevelItem>>

    @GET("api/periodos-dia")
    fun getPeriods(): Call<List<PeriodItem>>

    @GET("api/dias-semana")
    fun getWeekdays(): Call<List<WeekdayItem>>

    @GET("api/perfil")
    fun getProfile(): Call<ProfileResponse>

    @PUT("api/perfil")
    fun updateProfile(@Body request: UpdateProfileRequest): Call<Void>

    @GET("api/objetivos-fitness")
    fun getGoals(): Call<List<GoalItem>>

}
