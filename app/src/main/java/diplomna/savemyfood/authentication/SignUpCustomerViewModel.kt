package diplomna.savemyfood.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import diplomna.savemyfood.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpCustomerViewModel(private val authService: AuthService): ViewModel() {

    private val _state = MutableStateFlow<LinkState>(LinkState.None)
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

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    fun setConfirmPassword(confirmPasswordInput: String) {
        _confirmPassword.value = confirmPasswordInput
    }

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    fun setUsername(usernameInput: String) {
        _username.value = usernameInput
    }

    fun createAccount() {
        viewModelScope.launch {
            authService.signup(email.value, password.value, false) { user, error ->
                if (error == null && user != null) {
                    val userDoc = db.collection("users").document(user.uid)
                    val newUser = User(email.value, username.value, "",
                        0.0, 0.0, false, 0.0f)
                    userDoc.set(newUser)
                    _state.value = LinkState.Success
                } else {
                    _state.value = LinkState.Error
                }
            }
        }
    }

    fun reset()
    {
        _state.value = LinkState.None
    }


    sealed class LinkState {
        object None :  LinkState()
        object Success :  LinkState()
        object Error :  LinkState()
    }
}