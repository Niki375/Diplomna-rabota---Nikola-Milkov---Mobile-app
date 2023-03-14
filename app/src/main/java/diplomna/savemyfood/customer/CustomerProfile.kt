package diplomna.savemyfood.customer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CustomerProfilePage(
    onLogOutClick:() -> Unit,
    onDeleteAccount: () -> Unit,
    viewModel: CustomerProfileViewModel
) {

    viewModel.getUser()

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Profile", style = TextStyle(fontSize = 40.sp))
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Username: ${viewModel.user.value?.username}",
            style = TextStyle(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Email: ${viewModel.user.value?.email}",
            style = TextStyle(fontSize = 20.sp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Money: ${viewModel.user.value?.money}",
            style = TextStyle(fontSize = 20.sp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        val money = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = money.value,
            onValueChange = { money.value = it },
            label = { Text(text = "Money") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (money.value.text.isNotEmpty()) {
            Button(
                onClick = {
                    viewModel.addMoney(money.value.text.toFloat());
                    money.value = TextFieldValue()
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Add money")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { onLogOutClick(); viewModel.logOut() },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Log out")
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))

    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Delete account"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = {onDeleteAccount(); viewModel.deleteAccount()},
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
            )
        )
    }
}