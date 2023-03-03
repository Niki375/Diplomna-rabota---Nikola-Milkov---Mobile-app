package diplomna.savemyfood.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import diplomna.savemyfood.authentication.*
import diplomna.savemyfood.business.*
import diplomna.savemyfood.customer.*
import org.koin.androidx.compose.getViewModel


@Composable
fun NavHost(navController: NavHostController) {

    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {

            val viewModel = getViewModel<LoginViewModel>()

            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            val LoginState by viewModel.state.collectAsState()



            LoginPage(
                onForgotPasswordClick = { navController.navigate(Routes.ForgotPassword.route) },
                onSignUpHereClick = { navController.navigate(Routes.SignUpType.route) },
                onLoginClick = {viewModel.authenticate()},
                successfulLogin = {
                    viewModel.reset()
                },
                email = email, password = password,
                setEmail = { viewModel.setEmail(it) },
                setPassword = { viewModel.setPassword(it) },
                state = LoginState,
                navController = navController
            )
        }

        composable(Routes.SignUpType.route) {
            SignUpTypePage(
                onCustomerClick = {navController.navigate(Routes.CustomerSignUp.route)},
                onBusinessClick = {navController.navigate(Routes.BusinessSignUp.route)},
                onLoginClick = {navController.navigate(Routes.Login.route)})
        }

        composable(Routes.CustomerSignUp.route) {
            val viewModel = getViewModel<SignUpCustomerViewModel>()

            val username by viewModel.username.collectAsState()
            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            val linkState by viewModel.state.collectAsState()

            CustomerSignUpPage(
                onSignUpClick = {viewModel.createAccount()},
                onLoginClick = {navController.navigate(Routes.Login.route)},
                successfulSignUp = {
                    navController.navigate(Routes.CustomerHome.route)
                    viewModel.reset()
                },
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

            BusinessSignUpPage(
                onSignUpClick = {viewModel.createAccount()},
                onLoginClick = {navController.navigate(Routes.Login.route)},
                successfulSignUp = {
                    navController.navigate(Routes.BusinessHome.route)
                    viewModel.reset()
                },
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
            val viewModel = getViewModel<CustomerHomeViewModel>()

            CustomerHomePage(viewModel = viewModel)
        }

        composable(Routes.CustomerCart.route) {
            val viewModel = getViewModel<CustomerCartViewModel>()

            CustomerCartPage(viewModel = viewModel)
        }
        composable(Routes.CustomerMap.route) {
            CustomerMapPage()
        }
        composable(Routes.CustomerProfile.route) {
                val viewModel = getViewModel<CustomerProfileViewModel>()

            CustomerProfilePage(
                viewModel = viewModel,
                onLogOutClick = {navController.navigate(Routes.Login.route)},
                onDeleteAccount = {navController.navigate(Routes.Login.route)}
            )

        }


        composable(Routes.BusinessHome.route) {
            val viewModel = getViewModel<BusinessHomeViewModel>()

            BusinessHomePage(viewModel = viewModel, onAddBoxClick = {navController.navigate(Routes.BusinessSellBox.route)})
        }

        composable(Routes.BusinessSellBox.route) {
            BusinessSellBoxPage(navController = navController)

        }


        composable(Routes.BusinessProfile.route) {
            val viewModel = getViewModel<BusinessProfileViewModel>()

            BusinessProfilePage(
                onLogOutClick = {navController.navigate(Routes.Login.route)},
                onDeleteAccount = {navController.navigate(Routes.Login.route)},
                viewModel = viewModel)
        }

    }
}