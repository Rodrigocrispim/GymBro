package pt.iade.ei.gymbro.data.repository


import pt.iade.ei.gymbro.data.network.CreateOfferRequest
import pt.iade.ei.gymbro.data.network.CreateOfferResponse
import pt.iade.ei.gymbro.data.network.OfferResponse
import pt.iade.ei.gymbro.data.network.RetrofitInstance


sealed class CreateOfferResult {
    data class Success(val response: CreateOfferResponse) : CreateOfferResult()
    data class Error(val message: String) : CreateOfferResult()
}


sealed class OfferListResult {
    data class Success(val offers: List<OfferResponse>) : OfferListResult()
    data class Error(val message: String) : OfferListResult()
}


class OfferRepository {

    private val api = RetrofitInstance.api


    suspend fun createOffer(
        title: String, location: String, date: String, time: String,
        type: String, level: String, maxPeople: Int,
        description: String, userId: Long
    ): CreateOfferResult {

        val request = CreateOfferRequest(
            title, location, date, time, type, level, maxPeople, description, userId
        )
        return try {
            val response = api.createOffer(request)
            if (response.isSuccessful && response.body() != null) {
                CreateOfferResult.Success(response.body()!!)
            } else {
                CreateOfferResult.Error("Failed to create offer: ${response.message()}")
            }
        } catch (e: Exception) {
            CreateOfferResult.Error("Network error: ${e.message ?: "Unknown error"}")
        }
    }


    suspend fun getOffers(): OfferListResult {
        return try {
            val response = api.getOffers()

            if (response.isSuccessful && response.body() != null) {
                OfferListResult.Success(response.body()!!)
            } else {
                OfferListResult.Error("Failed to fetch offers: ${response.message()}")
            }
        } catch (e: Exception) {
            OfferListResult.Error("Network error: ${e.message ?: "Unknown error"}")
        }
    }
}