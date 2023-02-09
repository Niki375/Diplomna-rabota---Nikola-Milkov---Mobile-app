package com.example.safefood.ui.SignUpType

import ButtonTemplate
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safefood.R
import com.example.safefood.ui.theme.Purple700

@Composable
fun SignUpTypePage(onUserClick:() -> Unit, onShopClick:() -> Unit, onLoginClick:() -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Login"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = {(onLoginClick())},
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

        Text(text = "Select how you will use the app", textAlign = TextAlign.Center, style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        ButtonTemplate(onButtonClick = {onUserClick()}, text = stringResource(id = R.string.user_type))
        Spacer(modifier = Modifier.height(20.dp))
        ButtonTemplate(onButtonClick = {onShopClick()}, text = stringResource(id = R.string.shop_type))

        Text(text = "Already have an account?", textAlign = TextAlign.Center, style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))
    }
}