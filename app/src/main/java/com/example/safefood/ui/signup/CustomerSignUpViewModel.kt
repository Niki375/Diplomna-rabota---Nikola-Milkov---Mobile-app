package com.example.safefood.ui.signup

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
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
class CustomerSignUpViewModel @Inject constructor(
    private val repository: Authentication
): ViewModel(){

    val _signUpState = Channel<CustomerSignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUSer(email = String(), password = String()).collect{ result ->
            when(result){
                is Resource.Success ->{
                    _signUpState.send(CustomerSignUpState(isSuccess = "Sign In Success"))
                }
                is Resource.Loading ->{
                    _signUpState.send(CustomerSignUpState(isLoading = true))
                }
                is Resource.Error ->{
                    _signUpState.send(CustomerSignUpState(isError = result.message))
                }
            }
        }
    }
}