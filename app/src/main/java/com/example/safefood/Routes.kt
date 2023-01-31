package com.example.safefood

sealed class Routes(val route: String) {
    object CustomerSignUp : Routes("CustomerSignUp")
    object BusinessSignUp : Routes("BusinessSignUp")
    object ForgotPassword : Routes("ForgotPassword")
    object Login : Routes("Login")
    //object Dashboard : Routes("Dashboard")
    //object Home : Routes("Home")
    object SignUpType : Routes ("SignUpType")

}