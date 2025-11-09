package pt.iade.ei.gymbro.ui.screens.create_offer


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pt.iade.ei.gymbro.data.repository.CreateOfferResult
import pt.iade.ei.gymbro.data.repository.OfferRepository


data class CreateOfferUiState(
    val title: String = "",
    val location: String = "",
    val date: String = "",
    val time: String = "",
    val workoutType: String = "",
    val levelRequired: String = "Any Level",
    val maxPeople: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val createSuccess: Boolean = false
)


class CreateOfferViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CreateOfferUiState())
    val uiState = _uiState.asStateFlow()

    private val offerRepository = OfferRepository()


    fun onTitleChange(newTitle: String) {
        _uiState.update { it.copy(title = newTitle, error = null) }
    }
    fun onLocationChange(newLocation: String) {
        _uiState.update { it.copy(location = newLocation, error = null) }
    }
    fun onDateChange(newDate: String) {
        _uiState.update { it.copy(date = newDate, error = null) }
    }
    fun onTimeChange(newTime: String) {
        _uiState.update { it.copy(time = newTime, error = null) }
    }
    fun onWorkoutTypeChange(newType: String) {
        _uiState.update { it.copy(workoutType = newType, error = null) }
    }
    fun onLevelChange(newLevel: String) {
        _uiState.update { it.copy(levelRequired = newLevel, error = null) }
    }
    fun onMaxPeopleChange(newMaxPeople: String) {
        if (newMaxPeople.all { it.isDigit() }) {
            _uiState.update { it.copy(maxPeople = newMaxPeople, error = null) }
        }
    }
    fun onDescriptionChange(newDescription: String) {
        _uiState.update { it.copy(description = newDescription, error = null) }
    }


    fun onCreateOfferClicked() {
        val currentState = _uiState.value


        if (currentState.title.isBlank() || currentState.location.isBlank() ||
            currentState.date.isBlank() || currentState.time.isBlank() ||
            currentState.workoutType.isBlank() || currentState.maxPeople.isBlank()) {
            _uiState.update { it.copy(error = "Please fill in all required fields") }
            return
        }

        val maxPeopleInt = currentState.maxPeople.toIntOrNull()
        if (maxPeopleInt == null || maxPeopleInt <= 0) {
            _uiState.update { it.copy(error = "Invalid number for Max People") }
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val FAKE_USER_ID = 1L

            val result = offerRepository.createOffer(
                title = currentState.title,
                location = currentState.location,
                date = currentState.date,
                time = currentState.time,
                type = currentState.workoutType,
                level = currentState.levelRequired,
                maxPeople = maxPeopleInt,
                description = currentState.description,
                userId = FAKE_USER_ID
            )

            when (result) {
                is CreateOfferResult.Success -> {
                    _uiState.update { it.copy(isLoading = false, createSuccess = true) }
                }
                is CreateOfferResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }

    fun onNavigationDone() {
        _uiState.update { it.copy(createSuccess = false, error = null) }
    }
}