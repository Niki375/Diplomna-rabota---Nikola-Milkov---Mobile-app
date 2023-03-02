package diplomna.savemyfood.authentication

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import diplomna.savemyfood.service.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private val _user = MutableStateFlow<User?>(null)

    fun authenticate() {
        viewModelScope.launch {
            authService.authenticate(email.value, password.value) { error ->
                if (error == null) {
                    viewModelScope.launch(Dispatchers.IO) {
                        val user = authService.getUserData()
                        Log.d(TAG, "User is $user")
                        withContext(Dispatchers.Main) {
                            _user.value = user
                            _state.value = if (user?.business == true) {
                                Log.d(TAG, "User is a business")
                                LoginState.SuccessBusiness
                            } else {
                                Log.d(TAG, "User is a customer")
                                LoginState.SuccessCustomer
                            }
                        }
                    }
                } else {
                    _state.value = LoginState.Error
                }
            }
        }
    }

    fun reset() {
        _state.value = LoginState.None
    }

    sealed class LoginState {
        object None : LoginState()
        object SuccessCustomer : LoginState()
        object SuccessBusiness : LoginState()
        object Error : LoginState()
    }
}
