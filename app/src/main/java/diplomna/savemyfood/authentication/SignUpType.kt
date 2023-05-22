package diplomna.savemyfood.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diplomna.savemyfood.R
import diplomna.savemyfood.ui.theme.ButtonTemplate

@Composable
fun SignUpTypePage(onCustomerClick:() -> Unit, onBusinessClick:() -> Unit, onLoginClick:() -> Unit) {

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Select how you will use the app", textAlign = TextAlign.Center, style = TextStyle(fontSize = 40.sp))

        Spacer(modifier = Modifier.height(20.dp))
        ButtonTemplate(onButtonClick = {onCustomerClick()}, text = stringResource(id = R.string.customer))


        Spacer(modifier = Modifier.height(20.dp))
        ButtonTemplate(onButtonClick = {onBusinessClick()}, text = stringResource(id = R.string.business))

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