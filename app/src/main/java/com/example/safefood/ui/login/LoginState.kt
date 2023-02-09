package com.example.safefood.ui.login


data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""

)