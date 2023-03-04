package diplomna.savemyfood.business

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import diplomna.savemyfood.navigation.Routes

@Composable
fun BusinessSellBoxPage(navController: NavController) {

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val foodtype = remember { mutableStateOf(TextFieldValue()) }
        val description = remember { mutableStateOf(TextFieldValue()) }
        val pickup_time = remember { mutableStateOf(TextFieldValue()) }
        val price_per_box = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Add a box", style = TextStyle(textDecoration = TextDecoration.Underline, fontSize = 40.sp))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Food type") },
            value = foodtype.value,
            onValueChange = { foodtype.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Description") },
            value = description.value,
            onValueChange = { description.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Pickup time") },
            value = pickup_time.value,
            onValueChange = { pickup_time.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Price per box") },
            value = price_per_box.value,
            onValueChange = { price_per_box.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        if (foodtype.value.text.isNotEmpty() && description.value.text.isNotEmpty() && pickup_time.value.text.isNotEmpty() && price_per_box.value.text.isNotEmpty()) {
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        onSellBoxClick(
                            foodtype.value.text,
                            description.value.text,
                            pickup_time.value.text,
                            price_per_box.value.text.toFloat()
                        )
                        navController.navigate(Routes.BusinessHome.route)
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Sell box")
                }
            }
        }
    }
}