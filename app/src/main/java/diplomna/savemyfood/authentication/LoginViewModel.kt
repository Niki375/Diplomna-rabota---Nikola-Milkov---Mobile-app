package diplomna.savemyfood.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import diplomna.savemyfood.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val authService: AuthService): ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.None)
    val state = _state.asStateFlow()

    private val _email =
        MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun setEmail(emailInput: String) {
        _email.value = emailInput
    }

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun setPassword(passwordInput: String) {
        _password.value = passwordInput
    }

    fun authenticate() {
        viewModelScope.launch {
            authService.authenticate(email.value, password.value) { error ->
                if (error == null) {
                    _state.value = LoginState.Success
                } else {
                    _state.value = LoginState.Error
                }
            }
        }
    }

fun reset()
{
    _state.value = LoginState.None
}


    sealed class LoginState {
        object None :  LoginState()
        object Success :  LoginState()
        object Error :  LoginState()
    }
}