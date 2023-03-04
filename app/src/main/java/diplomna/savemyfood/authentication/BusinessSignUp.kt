package diplomna.savemyfood.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BusinessSignUpPage(onSignUpClick: () -> Unit,
                       onLoginClick: () -> Unit,
                       successfulSignUp: () -> Unit,
                       username: String,
                       email: String,
                       password: String,
                       address: String,
                       latitude: Double,
                       longitude: Double,
                       setUsername: (String) -> Unit,
                       setEmail: (String) -> Unit,
                       setPassword: (String) -> Unit,
                       setAddress: (String) -> Unit,
                       setLatitude: (Double) -> Unit,
                       setLongitude: (Double) -> Unit,
                       state: SignUpBusinessViewModel.LinkState
) {

    when (state) {
        SignUpBusinessViewModel.LinkState.Error -> {

        }
        SignUpBusinessViewModel.LinkState.None -> {
        }
        SignUpBusinessViewModel.LinkState.Success -> {
            successfulSignUp()
        }
    }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        val username = remember { mutableStateOf(TextFieldValue()) }
//        val email = remember { mutableStateOf(TextFieldValue()) }
//        val password = remember { mutableStateOf(TextFieldValue()) }
//        val confirmPassword = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Sign up", style = TextStyle(fontSize = 40.sp))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username,
            onValueChange = { setUsername(it) })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Email") },
            value = email,
            onValueChange = { setEmail(it) })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { setPassword(it) })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Address") },
            value = address,
            onValueChange = { setAddress(it) })

        Spacer(modifier = Modifier.height(20.dp))
        val latitude = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = latitude.value,
            onValueChange = { latitude.value = it; setLatitude(it.text.toDouble()) },
            label = { Text(text = "Latitude") }
        )

        Spacer(modifier = Modifier.height(20.dp))
        val longitude = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = longitude.value,
            onValueChange = { longitude.value = it; setLongitude(it.text.toDouble()) },
            label = { Text(text = "Longitude") }
        )

        /*Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Confirm Password") },
            value = confirmPassword.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { confirmPassword.value = it })*/

        Spacer(modifier = Modifier.height(20.dp))
        if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && address.isNotEmpty() && latitude.value.text.isNotEmpty() && longitude.value.text.isNotEmpty()) {
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(onClick = {
                    // Call the createAccount function with the email and password
//                EmailPasswordActivity().createAccount(email.value.text, password.value.text)
                    onSignUpClick()
                }) {
                    Text("Create Account")
                }
            }
        }

        Text(text = "Already have an account?", textAlign = TextAlign.Center, style = TextStyle(fontSize = 15.sp))


        Box(modifier = Modifier.fillMaxSize()) {
            ClickableText(
                text = AnnotatedString("Login"),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                onClick = {onLoginClick()},
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                )
            )
        }
    }
}
