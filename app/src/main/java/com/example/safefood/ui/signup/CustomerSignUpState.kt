package com.example.safefood.ui.signup


data class CustomerSignUpState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""

)