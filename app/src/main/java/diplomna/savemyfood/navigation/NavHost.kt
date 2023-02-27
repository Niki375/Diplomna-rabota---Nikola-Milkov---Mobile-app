package diplomna.savemyfood.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import diplomna.savemyfood.authentication.*
import diplomna.savemyfood.business.BusinessHomePage
import diplomna.savemyfood.business.BusinessProfilePage
import diplomna.savemyfood.business.BusinessSellBoxPage
import diplomna.savemyfood.customer.CustomerCartPage
import diplomna.savemyfood.customer.CustomerHomePage
import diplomna.savemyfood.customer.CustomerMapPage
import diplomna.savemyfood.customer.CustomerProfilePage
import org.koin.androidx.compose.getViewModel


@Composable
fun NavHost(navController: NavHostController) {

    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginPage(onLoginClick = {},
                onForgotPasswordClick = {navController.navigate(Routes.ForgotPassword.route)},
                onSignUpHereClick = {navController.navigate(Routes.SignUpType.route)})
        }
        composable(Routes.SignUpType.route) {
            SignUpTypePage(
                onCustomerClick = {navController.navigate(Routes.CustomerSignUp.route)},
                onBusinessClick = {navController.navigate(Routes.BusinessSignUp.route)},
                onLoginClick = {navController.navigate(Routes.Login.route)})
        }

        composable(Routes.CustomerSignUp.route) {
            val viewModel = getViewModel<SignUpViewModel>()

            val username by viewModel.username.collectAsState()
            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            val linkState by viewModel.state.collectAsState()

            CustomerSignUpPage(onSignUpClick = {viewModel.createAccount(); navController.navigate(Routes.CustomerHome.route )
            }
                , onLoginClick = {navController.navigate(Routes.Login.route)},
            successfulSignUp = { navController.navigate(Routes.CustomerHome.route) },
            username = username, email = email, password = password,
            setUsername = {viewModel.setUsername(it)},
            setEmail = {viewModel.setEmail(it)},
            setPassword = {viewModel.setPassword(it)},
            state = linkState)
        }

        composable(Routes.BusinessSignUp.route) {
            val viewModel = getViewModel<SignUpBusinessViewModel>()

            val username by viewModel.username.collectAsState()
            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            val address by viewModel.address.collectAsState()
            val LinkState by viewModel.state.collectAsState()

            BusinessSignUpPage(onSignUpClick = {viewModel.createAccount(); navController.navigate(Routes.BusinessHome.route )
            }
                , onLoginClick = {navController.navigate(Routes.Login.route)},
                successfulSignUp = { navController.navigate(Routes.BusinessHome.route) },
                username = username, email = email, password = password, address = address,
                setUsername = {viewModel.setUsername(it)},
                setEmail = {viewModel.setEmail(it)},
                setPassword = {viewModel.setPassword(it)},
                setAddress = {viewModel.setAddress(it)},
                state = LinkState)
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