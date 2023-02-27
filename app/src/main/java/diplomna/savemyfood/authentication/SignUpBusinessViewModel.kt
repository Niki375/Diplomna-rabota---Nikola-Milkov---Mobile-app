package diplomna.savemyfood.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import diplomna.savemyfood.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class SignUpBusinessViewModel(private val authService: AuthService): ViewModel() {

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

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    fun setUsername(usernameInput: String) {
        _username.value = usernameInput
    }

    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address

    fun setAddress(addressInput: String) {
        _address.value = addressInput
    }

    fun createAccount() {
        viewModelScope.launch {
            authService.signup(email.value, password.value) { user, error ->
                if (error == null && user != null) {
                    // success
                    _state.value = LinkState.Success
                } else {
                    // set error
                    _state.value = LinkState.Error
                }
            }
        }
    }




    sealed class LinkState {
        object None :  LinkState()
        object Success :  LinkState()
        object Error :  LinkState()
    }
}