package com.example.safefood.ui.signup

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.safefood.ui.theme.Purple700
import kotlinx.coroutines.launch

@Composable
fun CustomerSignUpPage(viewModel: CustomerSignUpViewModel = hiltViewModel(), onLoginClick:() -> Unit, onSignUpHereCLick:() -> Unit) {

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
                color = Purple700
            )
        )
    }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val email = remember { mutableStateOf(TextFieldValue()) }
        val phone_number = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val confirm_password = remember { mutableStateOf(TextFieldValue()) }
        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        val state = viewModel.signUpState.collectAsState(initial = null)


        Text(text = "SignUp", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Email") },
            value = email.value,
            onValueChange = { email.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Phone Number") },
            value = phone_number.value,
            onValueChange = { phone_number.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Confirm Password") },
            value = confirm_password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { confirm_password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        //ButtonTemplate(onButtonClick = {onSignUpHereCLick()}, text = stringResource(id = R.string.signup))

        Button(
            onClick = {
                scope.launch {
                    viewModel.registerUser(email = String(), password = String())
                }
            }
        ) {}

        Text(text = "Already have an account?", textAlign = TextAlign.Center, style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            if (state.value?.isLoading == true){
                CircularProgressIndicator()
            }
        }

        LaunchedEffect(key1 = state.value?.isSuccess){
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty() == true){
                    val success = state.value?.isSuccess
                    Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                }
            }
        }

        LaunchedEffect(key1 = state.value?.isError){
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true){
                    val error = state.value?.isError
                    Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}