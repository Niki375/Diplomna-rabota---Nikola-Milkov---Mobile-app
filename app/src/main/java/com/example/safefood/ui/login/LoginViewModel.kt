package com.example.safefood.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safefood.firebase.Resource
import com.example.safefood.firebase.data.Authentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Authentication
): ViewModel(){

    val _loginState = Channel<LoginState>()
    val loginState = _loginState.receiveAsFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUSer(email = String(), password = String()).collect{result ->
            when(result){
                is Resource.Success ->{
                     _loginState.send(LoginState(isSuccess = "Sign In Success"))
                }
                is Resource.Loading ->{
                    _loginState.send(LoginState(isLoading = true))
                }
                is Resource.Error ->{
                    _loginState.send(LoginState(isError = result.message))
                }
            }
        }
    }

}