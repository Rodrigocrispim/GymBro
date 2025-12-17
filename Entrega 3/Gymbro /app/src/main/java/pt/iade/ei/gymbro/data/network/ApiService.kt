package pt.iade.ei.gymbro.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("api/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("api/auth/register")
    fun register(@Body request: RegisterRequest): Call<Void>


    @GET("api/ofertas")
    fun getOffers(): Call<List<OfferResponse>>

    @POST("api/ofertas")
    fun createOffer(@Body request: CreateOfferRequest): Call<ResponseBody>


    @POST("api/candidaturas")
    fun applyToOffer(@Body request: CreateApplicationRequest): Call<Void>

    @GET("api/candidaturas/usuario/{usuarioId}")
    fun getUserApplications(@Path("usuarioId") userId: Int): Call<List<ApplicationResponse>>

    @GET("api/ofertas/{ofertaId}/candidaturas")
    fun getOfferApplications(@Path("ofertaId") ofertaId: Int): Call<List<ApplicationResponse>>

    @PUT("api/candidaturas/{id}/status")
    fun updateApplicationStatus(@Path("id") id: Int, @Body request: UpdateStatusRequest): Call<Void>


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

    @GET("api/objetivos-fitness")
    fun getGoals(): Call<List<GoalItem>>


    @GET("api/utilizadores/me")
    fun getUserProfile(): Call<ProfileResponse>


    @PUT("api/utilizadores/me")
    fun updateProfile(@Body request: UpdateProfileRequest): Call<Void>
}