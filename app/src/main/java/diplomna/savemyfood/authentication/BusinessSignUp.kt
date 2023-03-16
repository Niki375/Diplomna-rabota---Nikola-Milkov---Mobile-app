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
import androidx.compose.ui.graphics.Color
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
fun BusinessSignUpPage(
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    successfulSignUp: () -> Unit,

    username: String,
    email: String,
    address: String,
    password: String,
    confirmPassword: String,

    setUsername: (String) -> Unit,
    setEmail: (String) -> Unit,
    setAddress: (String) -> Unit,
    setPassword: (String) -> Unit,
    setConfirmPassword: (String) -> Unit,
    setLatitude: (Double) -> Unit,
    setLongitude: (Double) -> Unit,

    state: SignUpBusinessViewModel.LinkState
) {

    val latitude = remember {
        mutableStateOf(TextFieldValue())
    }

    val longitude = remember {
        mutableStateOf(TextFieldValue())
    }

    when (state) {
        SignUpBusinessViewModel.LinkState.Error -> {
            // handle error state
        }

        SignUpBusinessViewModel.LinkState.None -> {
            // handle none state
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

        Text(text = "Sign up", style = TextStyle(fontSize = 40.sp))

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = {
                Text(text = "Username")
            },
            value = username,
            onValueChange = {
                setUsername(it)
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = {
                Text(text = "Email")
            },
            value = email,
            onValueChange = {
                setEmail(it)
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = {
                Text(text = "Address")
            },
            value = address,
            onValueChange = {
                setAddress(it)
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = {
                Text(text = "Password")
            },
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                setPassword(it)
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = {
                Text(text = "Confirm Password")
            },
            value = confirmPassword,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                setConfirmPassword(it)
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = {
                Text(text = "Latitude")
            },
            value = latitude.value,
            onValueChange = {
                latitude.value = it;
                setLatitude(it.text.toDouble())
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = {
                Text(text = "Longitude")
            },
            value = longitude.value,
            onValueChange = {
                longitude.value = it;
                setLongitude(it.text.toDouble())
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        if (
            username.isNotEmpty() &&
            email.isNotEmpty() &&
            password.isNotEmpty() &&
            address.isNotEmpty() &&
            latitude.value.text.isNotEmpty() &&
            longitude.value.text.isNotEmpty() &&
            confirmPassword == password
        ) {
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                    onSignUpClick()
                    }
                ) {
                    Text("Create Account")
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Already have an account?",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 15.sp)
            )
            ClickableText(
                text = AnnotatedString("Login"),
                modifier = Modifier.padding(start = 10.dp),
                onClick = {
                    onLoginClick()
                },
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Cursive,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue
                )
            )
        }
    }
}
