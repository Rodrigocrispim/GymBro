package pt.iade.ei.gymbro.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// APAGÁMOS o 'delay'
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
// IMPORTAMOS O REPOSITÓRIO E O RESULTADO
import pt.iade.ei.gymbro.data.repository.AuthRepository
import pt.iade.ei.gymbro.data.repository.LoginResult


data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginSuccess: Boolean = false
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()


    private val authRepository = AuthRepository()


    fun onEmailChange(newEmail: String) {
        _uiState.update { currentState ->
            currentState.copy(email = newEmail, error = null)
        }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(password = newPassword, error = null)
        }
    }


    fun onLoginClicked() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {

            val result = authRepository.login(
                email = _uiState.value.email,
                password = _uiState.value.password
            )


            when (result) {
                is LoginResult.Success -> {


                    _uiState.update {
                        it.copy(isLoading = false, loginSuccess = true)
                    }
                }
                is LoginResult.Error -> {

                    _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    private fun AuthRepository.login(
        email: String,
        password: String
    ) {
    }


    fun onNavigationDone() {
        _uiState.update { it.copy(loginSuccess = false, error = null) }
    }
}