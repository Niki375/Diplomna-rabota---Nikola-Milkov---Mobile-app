package com.example.safefood.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.safefood.Routes
import com.example.safefood.ui.Forgot_Password.ForgotPasswordPage
import com.example.safefood.ui.SignUpType.SignUpTypePage
import com.example.safefood.ui.login.LoginPage
import com.example.safefood.ui.signup.BusinessSignUpPage
import com.example.safefood.ui.signup.CustomerSignUpPage
import com.example.safefood.ui.user.UserCartPage
import com.example.safefood.ui.user.UserHomePage
import com.example.safefood.ui.user.UserMapPage
import com.example.safefood.ui.user.UserProfilePage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NavHost(navController: NavHostController){

    val backStackEntry = navController.currentBackStackEntryAsState()


    NavHost(navController = navController,
        startDestination = Routes.Login.route) {

            composable(Routes.Login.route) {
                LoginPage(onLoginClick = {navController.navigate(Routes.Login.route)}, onForgotPasswordCLick = {navController.navigate(Routes.ForgotPassword.route)}, onSignUpHereCLick = {navController.navigate(Routes.SignUpType.route)})
            }

            composable(Routes.CustomerSignUp.route) {
                CustomerSignUpPage(onSignUpHereCLick = {navController.navigate(Routes.UserHome.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
            }

            composable(Routes.BusinessSignUp.route) {
                BusinessSignUpPage(onSignUpHereCLick = {navController.navigate(Routes.Login.route)}, onLoginClick = {navController.navigate(Routes.Login.route)})
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
                UserProfilePage(onLogOutClick = {}, onDeleteAccount = {})
            }

            composable(Routes.UserMap.route) {
                UserMapPage()
            }
    }

    /*Scaffold(
        bottomBar = {
            androidx.compose.material3.NavigationBar(
                containerColor = MaterialTheme.colors.primary,
            ) {
                bottomNavItems.forEach { item ->
                    val selected = item.route == backStackEntry.value?.destination?.route

                    NavigationBarItem(
                        selected = selected,
                        onClick = { navController.navigate(item.route) },
                        label = {
                            Text(
                                text = item.name,
                                fontWeight = FontWeight.SemiBold,
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "${item.name} Icon",
                            )
                        }
                    )
                }
            }
        },
        content = {
        }
    )*/
}
