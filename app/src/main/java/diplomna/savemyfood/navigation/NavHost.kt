package diplomna.savemyfood.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import diplomna.savemyfood.business.BusinessSellBoxPage
import diplomna.savemyfood.authentication.*
import diplomna.savemyfood.business.BusinessHomePage
import diplomna.savemyfood.business.BusinessProfilePage
import diplomna.savemyfood.customer.CustomerCartPage
import diplomna.savemyfood.customer.CustomerHomePage
import diplomna.savemyfood.customer.CustomerMapPage
import diplomna.savemyfood.customer.CustomerProfilePage


@Composable
fun NavHost(navController: NavHostController) {

    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginPage(onLoginClick = {}, onForgotPasswordClick = {navController.navigate(Routes.ForgotPassword.route)}, onSignUpHereClick = {navController.navigate(Routes.SignUpType.route)})
        }
        composable(Routes.SignUpType.route) {
            SignUpTypePage(onCustomerClick = {navController.navigate(Routes.CustomerSignUp.route)}, onBusinessClick = {navController.navigate(Routes.BusinessSignUp.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
        }
        composable(Routes.CustomerSignUp.route) {
            CustomerSignUpPage(onSignUpClick = {navController.navigate(Routes.CustomerHome.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
        }
        composable(Routes.BusinessSignUp.route) {
            BusinessSignUpPage(onSignUpClick = {navController.navigate(Routes.BusinessHome.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
        }
        composable(Routes.ForgotPassword.route) {
            ForgotPasswordPage(onSubmitClick = {})
        }


        composable(Routes.CustomerHome.route) {
            CustomerHomePage()
        }
        composable(Routes.CustomerCart.route) {
            CustomerCartPage()
        }
        composable(Routes.CustomerMap.route) {
            CustomerMapPage()
        }
        composable(Routes.CustomerProfile.route) {
            CustomerProfilePage(onLogOutClick = {navController.navigate(Routes.Login.route)}, onDeleteAccount = {navController.navigate(Routes.Login.route)})
        }


        composable(Routes.BusinessHome.route) {
            BusinessHomePage(onAddBoxClick = {navController.navigate(Routes.BusinessSellBox.route)})
        }
        composable(Routes.BusinessSellBox.route) {
            BusinessSellBoxPage(onSellBoxClick = {navController.navigate(Routes.BusinessHome.route)})
        }
        composable(Routes.BusinessProfile.route) {
            BusinessProfilePage(onLogOutClick = {navController.navigate(Routes.Login.route)}, onDeleteAccount = {navController.navigate(Routes.Login.route)})
        }

    }
}