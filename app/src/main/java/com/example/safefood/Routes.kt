package com.example.safefood

sealed class Routes(val route: String) {
    object CustomerSignUp : Routes("CustomerSignUp")
    object BusinessSignUp : Routes("BusinessSignUp")
    object ForgotPassword : Routes("ForgotPassword")
    object Login : Routes("Login")
    object SignUpType : Routes ("SignUpType")
    object UserHome : Routes ("UserHome")
    object UserCart: Routes ("UserCart")
    object UserProfile: Routes ("UserProfile")
    object UserMap: Routes ("UserMap")

}