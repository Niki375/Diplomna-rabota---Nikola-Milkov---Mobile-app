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

    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address

    fun setAddress(addressInput: String) {
        _address.value = addressInput
    }

    private val _latitude = MutableStateFlow(0.0)
    val latitude: StateFlow<Double> = _latitude

    fun setLatitude(latitudeInput: Double) {
        _latitude.value = latitudeInput
    }

    private val _longitude = MutableStateFlow(0.0)
    val longitude: StateFlow<Double> = _longitude

    fun setLongitude(longitudeInput: Double) {
        _longitude.value = longitudeInput
    }

    fun createAccount() {
        viewModelScope.launch {
            authService.signup(email.value, password.value, true) { user, error ->
                if (error == null && user != null) {
                    val userDoc = db.collection("users").document(user.uid)
                    val newUser = User(email.value, username.value, address.value, latitude.value,
                        longitude.value, true)
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