package com.example.safefood.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.safefood.Routes
import com.example.safefood.ui.Forgot_Password.ForgotPasswordPage
import com.example.safefood.ui.SignUpType.SignUpTypePage
import com.example.safefood.ui.login.LoginPage
import com.example.safefood.ui.shop.ShopAddBoxPage
import com.example.safefood.ui.shop.ShopHomePage
import com.example.safefood.ui.shop.ShopProfilePage
import com.example.safefood.ui.signup.BusinessSignUpPage
import com.example.safefood.ui.signup.CustomerSignUpPage
import com.example.safefood.ui.user.UserCartPage
import com.example.safefood.ui.user.UserHomePage
import com.example.safefood.ui.user.UserMapPage
import com.example.safefood.ui.user.UserProfilePage

@Composable
fun NavHost(navController: NavHostController){

    NavHost(navController = navController,
        startDestination = Routes.Login.route) {

            composable(Routes.Login.route) {
                LoginPage(onLoginClick = {navController.navigate(Routes.Login.route)}, onForgotPasswordCLick = {navController.navigate(Routes.ForgotPassword.route)}, onSignUpHereCLick = {navController.navigate(Routes.SignUpType.route)})
            }

            composable(Routes.CustomerSignUp.route) {
                CustomerSignUpPage(onSignUpHereCLick = {navController.navigate(Routes.UserHome.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
            }

            composable(Routes.BusinessSignUp.route) {
                BusinessSignUpPage(onSignUpHereCLick = {navController.navigate(Routes.ShopHome.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
            }

            composable(Routes.ForgotPassword.route) {
                ForgotPasswordPage(onSubmitClick = {})
            }

            composable(Routes.SignUpType.route) {
                SignUpTypePage(onUserClick = {navController.navigate(Routes.CustomerSignUp.route)}, onShopClick = {navController.navigate(Routes.BusinessSignUp.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
            }

            composable(Routes.UserHome.route) {
                UserHomePage()
            }

            composable(Routes.UserCart.route) {
                UserCartPage()
            }

            composable(Routes.UserProfile.route) {
                UserProfilePage(onLogOutClick = {navController.navigate(Routes.Login.route)}, onDeleteAccount = {navController.navigate(Routes.Login.route)})
            }

            composable(Routes.UserMap.route) {
                UserMapPage()
            }

            composable(Routes.ShopHome.route) {
                ShopHomePage(onAddBoxClick = {navController.navigate(Routes.ShopAddBox.route)})
            }

            composable(Routes.ShopProfile.route) {
                ShopProfilePage(onLogOutClick = {navController.navigate(Routes.Login.route)}, onDeleteAccount = {navController.navigate(Routes.Login.route)})
            }

            composable(Routes.ShopAddBox.route) {
                ShopAddBoxPage(onSellBoxClick = {navController.navigate(Routes.ShopHome.route)})
            }
    }
}
