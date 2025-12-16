package pt.iade.ei.gymbro.data.network

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pt.iade.ei.gymbro.ui.utils.SessionManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Agora a BASE_URL é o backend local na máquina host
    private const val BASE_URL = "https://bannered-transmarine-roni.ngrok-free.dev/"

    // 1. GSON permissivo
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    // 2. Cliente HTTP com logger + token
    private val client by lazy {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val original = chain.request()
                val token = SessionManager.token

                val requestBuilder = original.newBuilder()
                    // este header era só para ngrok; podes remover ou deixar, não faz diferença aqui
                    // .addHeader("ngrok-skip-browser-warning", "true")
                    .method(original.method, original.body)

                if (!token.isNullOrEmpty()) {
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                    Log.d("API_AUTH", "Token anexado: Bearer ${token.take(10)}...")
                }

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}
