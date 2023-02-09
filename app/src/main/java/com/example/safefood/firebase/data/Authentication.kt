package com.example.safefood.firebase.data

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.flow.Flow
import com.example.safefood.firebase.Resource
import com.google.firebase.auth.AuthResult


interface Authentication {
    fun loginUSer(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
}