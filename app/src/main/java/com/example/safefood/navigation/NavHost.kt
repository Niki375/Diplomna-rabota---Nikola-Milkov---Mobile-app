package com.example.safefood.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.safefood.Routes
import com.example.safefood.ui.Forgot_Password.ForgotPasswordPage
import com.example.safefood.ui.SignUpType.SignUpTypePage
import com.example.safefood.ui.login.LoginPage
import com.example.safefood.ui.signup.BusinessSignUpPage
import com.example.safefood.ui.signup.CustomerSignUpPage

@Composable
fun NavHost(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = Routes.Login.route) {

            composable(Routes.Login.route) {
                LoginPage(onLoginClick = {navController.navigate(Routes.Login.route)}, onForgotPasswordCLick = {navController.navigate(Routes.ForgotPassword.route)}, onSignUpHereCLick = {navController.navigate(Routes.SignUpType.route)})
            }

            composable(Routes.CustomerSignUp.route) {
                CustomerSignUpPage(onSignUpHereCLick = {navController.navigate(Routes.Login.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
            }

            composable(Routes.BusinessSignUp.route) {
                BusinessSignUpPage(onSignUpHereCLick = {navController.navigate(Routes.Login.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
            }

            composable(Routes.ForgotPassword.route) { navBackStack ->
                ForgotPasswordPage(onSubmitClick = {})
            }

            composable(Routes.SignUpType.route) {
                SignUpTypePage(onUserClick = {navController.navigate(Routes.CustomerSignUp.route)}, onShopClick = {navController.navigate(Routes.BusinessSignUp.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
            }

            /*composable(Routes.Dashboard.route) {
               // Dashboard(navController = navController)
            }

            composable(Routes.Home.route) {
               // Home(navController = navController)
            }*/
    }
}