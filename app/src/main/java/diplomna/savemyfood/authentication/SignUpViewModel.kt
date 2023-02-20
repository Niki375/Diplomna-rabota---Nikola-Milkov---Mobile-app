package diplomna.savemyfood.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import diplomna.savemyfood.LinkAccountUseCase
import diplomna.savemyfood.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val authService: AuthService): ViewModel() {

    private val _state = MutableStateFlow<LinkState>(LinkState.None)
    val state = _state.asStateFlow()

    private val _email =
        MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun email(emailInput: String) {
        _email.value = emailInput
    }

    private val _pass =
        MutableStateFlow("")
    val pass: StateFlow<String> = _pass

    fun _pass(pass: String) {
        _pass.value = pass
    }

    private val _username =
        MutableStateFlow("")
    val username: StateFlow<String> = _username

    fun username(username: String) {
        _username.value = username
    }

    fun linkAccount() {
        viewModelScope.launch {
            authService.linkAccount(email.value, pass.value) { error ->
                if (error == null) {
                    //success
                    _state.value =LinkState.Success
                } else {
                    //set error
                    _state.value =LinkState.Error

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