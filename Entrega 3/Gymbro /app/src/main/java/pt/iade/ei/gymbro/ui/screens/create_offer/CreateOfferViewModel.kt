package pt.iade.ei.gymbro.ui.screens.create_offer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pt.iade.ei.gymbro.data.network.*
import pt.iade.ei.gymbro.ui.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import okhttp3.ResponseBody

class CreateOfferViewModel : ViewModel() {


    var title by mutableStateOf("")
    var description by mutableStateOf("")


    var selectedLocationId by mutableIntStateOf(0)
    var selectedLevelId by mutableIntStateOf(0)
    var selectedTypeId by mutableIntStateOf(0)
    var selectedWeekdayId by mutableIntStateOf(0)
    var selectedTimePeriodId by mutableIntStateOf(0)


    var locations by mutableStateOf<List<Dropdownable>>(emptyList())
    var workoutTypes by mutableStateOf<List<Dropdownable>>(emptyList())
    var levels by mutableStateOf<List<Dropdownable>>(emptyList())
    var periods by mutableStateOf<List<Dropdownable>>(emptyList())
    var weekdays by mutableStateOf<List<Dropdownable>>(emptyList())

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    init {
        fetchAllDropdownData()
    }

    private fun fetchAllDropdownData() {
        isLoading = true
        var pendingRequests = 5

        val checkFinished = {
            pendingRequests--
            if (pendingRequests <= 0) isLoading = false
        }


        RetrofitInstance.api.getLocations().enqueue(object : Callback<List<LocationItem>> {
            override fun onResponse(call: Call<List<LocationItem>>, response: Response<List<LocationItem>>) {
                locations = response.body() ?: emptyList()
                if (locations.isNotEmpty()) selectedLocationId = locations[0].id
                checkFinished()
            }
            override fun onFailure(call: Call<List<LocationItem>>, t: Throwable) { checkFinished() }
        })


        RetrofitInstance.api.getWorkoutTypes().enqueue(object : Callback<List<WorkoutTypeItem>> {
            override fun onResponse(call: Call<List<WorkoutTypeItem>>, response: Response<List<WorkoutTypeItem>>) {
                workoutTypes = response.body() ?: emptyList()
                if (workoutTypes.isNotEmpty()) selectedTypeId = workoutTypes[0].id
                checkFinished()
            }
            override fun onFailure(call: Call<List<WorkoutTypeItem>>, t: Throwable) { checkFinished() }
        })


        RetrofitInstance.api.getLevels().enqueue(object : Callback<List<LevelItem>> {
            override fun onResponse(call: Call<List<LevelItem>>, response: Response<List<LevelItem>>) {
                levels = response.body() ?: emptyList()
                if (levels.isNotEmpty()) selectedLevelId = levels[0].id
                checkFinished()
            }
            override fun onFailure(call: Call<List<LevelItem>>, t: Throwable) { checkFinished() }
        })


        RetrofitInstance.api.getPeriods().enqueue(object : Callback<List<PeriodItem>> {
            override fun onResponse(call: Call<List<PeriodItem>>, response: Response<List<PeriodItem>>) {
                periods = response.body() ?: emptyList()
                if (periods.isNotEmpty()) selectedTimePeriodId = periods[0].id
                checkFinished()
            }
            override fun onFailure(call: Call<List<PeriodItem>>, t: Throwable) { checkFinished() }
        })


        RetrofitInstance.api.getWeekdays().enqueue(object : Callback<List<WeekdayItem>> {
            override fun onResponse(call: Call<List<WeekdayItem>>, response: Response<List<WeekdayItem>>) {
                weekdays = response.body() ?: emptyList()
                if (weekdays.isNotEmpty()) selectedWeekdayId = weekdays[0].id
                checkFinished()
            }
            override fun onFailure(call: Call<List<WeekdayItem>>, t: Throwable) { checkFinished() }
        })
    }

    fun createOffer(onSuccess: () -> Unit) {
        val userId = SessionManager.userId




        isLoading = true
        errorMessage = null

        val request = CreateOfferRequest(
            title = title,
            description = description,

            date = "",
            duration = 60.0,


            weekdayId = selectedWeekdayId,
            periodId = selectedTimePeriodId,

            locationId = selectedLocationId,
            workoutTypeId = selectedTypeId,
            levelId = selectedLevelId,

            userId = userId
        )

        Log.d("CreateOffer", "Enviando: $request")


        RetrofitInstance.api.createOffer(request).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                isLoading = false
                if (response.isSuccessful) {

                    Log.d("CreateOffer", "Sucesso: ${response.body()?.string()}")
                    onSuccess()
                } else {
                    val errorStr = response.errorBody()?.string()
                    errorMessage = "Erro ${response.code()}"
                    Log.e("CreateOffer", "Erro API: $errorStr")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                isLoading = false
                errorMessage = "Erro de rede: ${t.message}"
                Log.e("CreateOffer", "Falha: ${t.message}")
            }
        })
    }
}