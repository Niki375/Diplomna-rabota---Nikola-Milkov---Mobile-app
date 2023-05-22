package diplomna.savemyfood.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import diplomna.savemyfood.R
import diplomna.savemyfood.navigation.Routes
import diplomna.savemyfood.ui.theme.ButtonTemplate

@Composable
fun LoginPage(
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpHereClick:() -> Unit,
    successfulLogin: () -> Unit,
    email: String,
    password: String,
    setEmail: (String) -> Unit,
    setPassword: (String) -> Unit,
    state: LoginViewModel.LoginState,
    navController: NavController
) {

    when (state) {
        LoginViewModel.LoginState.None -> {
        }

        LoginViewModel.LoginState.Error -> {
        }

        LoginViewModel.LoginState.SuccessCustomer -> {
            successfulLogin()
            navController.navigate(Routes.CustomerHome.route)
        }

        LoginViewModel.LoginState.SuccessBusiness -> {
            successfulLogin()
            navController.navigate(Routes.BusinessHome.route)
        }
    }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Login", style = TextStyle(fontSize = 40.sp))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Email") },
            value = email,
            onValueChange = { setEmail(it) }
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { setPassword(it) }
        )

        Spacer(modifier = Modifier.height(20.dp))
        if(email.isNotEmpty() && password.isNotEmpty()) {
            ButtonTemplate(onButtonClick = {onLoginClick()}, text = stringResource(id = R.string.login))
        }else(
            Text(
                text = "Please fill all fields",
                style = TextStyle(color = Color.Red, fontSize = 12.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        )

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {onForgotPasswordClick()},
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.Default
            )
        )

        Box(modifier = Modifier.fillMaxSize()) {
            ClickableText(
                text = AnnotatedString("Sign up here"),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                onClick = {onSignUpHereClick()},
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                )
            )
        }
    }
}

