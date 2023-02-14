package diplomna.savemyfood.navigation

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object SignUpType : Routes("SignUpType")
    object CustomerSignUp : Routes("CustomerSignUp")
    object BusinessSignUp : Routes("BusinessSignUp")
    object ForgotPassword : Routes("ForgotPassword")


    object CustomerHome : Routes("CustomerHome")
    object CustomerCart : Routes("CustomerCart")
    object CustomerMap : Routes("CustomerMap")
    object CustomerProfile : Routes("CustomerProfile")


    object BusinessHome : Routes("BusinessHome")
    object BusinessSellBox : Routes("BusinessAddBox")
    object BusinessProfile : Routes("BusinessProfile")
}