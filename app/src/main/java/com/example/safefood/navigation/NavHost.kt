package com.example.safefood.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.safefood.Routes
import com.example.safefood.ui.Forgot_Password.ForgotPasswordPage
import com.example.safefood.ui.SignUpType.SignUpTypePage
import com.example.safefood.ui.login.LoginPage
import com.example.safefood.ui.signup.SignUpPage

@Composable
fun NavHost(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = Routes.Login.route) {

            composable(Routes.Login.route) {
                LoginPage(onLoginClick = {navController.navigate(Routes.Login.route)}, onForgotPassordCLick = {navController.navigate(Routes.ForgotPassword.route)}, onSignUpHereCLick = {navController.navigate(Routes.SignUpType.route)})
            }

            composable(Routes.SignUp.route) {
                SignUpPage(onSignUpHereCLick = {navController.navigate(Routes.Login.route)})
            }

            composable(Routes.ForgotPassword.route) { navBackStack ->
                ForgotPasswordPage(onSubmitClick = {})
            }

            composable(Routes.SignUpType.route) {
                SignUpTypePage(onUserClick = {navController.navigate((Routes.SignUp.route))}, onShopClick = {})
            }

            composable(Routes.Dashboard.route) {
               // Dashboard(navController = navController)
            }

            composable(Routes.Home.route) {
               // Home(navController = navController)
            }
    }
}