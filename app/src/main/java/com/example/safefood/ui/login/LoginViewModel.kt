/*package com.example.safefood.ui.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/*class LoginViewModel(private val firebaseAuth: FirebaseAuth) {
    private val auth = Firebase.auth
    var email = ""
    var password = ""


    fun onLoginClick(){
        auth.signInWithEmailAndPassword(email, password);
    }

    fun onForgotPasswordCLick(){
        auth.sendPasswordResetEmail(email)
    }

    fun onSignUpHereCLick(){
        auth.createUserWithEmailAndPassword(email,password)
    }
}*/

class LoginViewModel{
    private val firebaseAuth = FirebaseAuth.getInstance()
    var email = ""
    var password = ""

    fun onLoginClick() {
                firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Login successful
                } else {
                    // Login failed
                }
            }
    }

    fun onForgotPasswordClick() {
        // Show forgot password dialog
    }

    fun onSignUpHereClick() {
        // Navigate to Sign Up screen
    }
}*/