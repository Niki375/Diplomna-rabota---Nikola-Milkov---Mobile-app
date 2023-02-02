package com.example.safefood.ui.shop

import ButtonTemplate
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safefood.R

@Composable
fun ShopAddBoxPage(onSellBoxClick:() -> Unit) {

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val foodtype = remember { mutableStateOf(TextFieldValue()) }
        val description = remember { mutableStateOf(TextFieldValue()) }
        val pickup_time = remember { mutableStateOf(TextFieldValue()) }
        val price_per_box = remember { mutableStateOf(TextFieldValue()) }
        val quantity_of_boxes = remember { mutableStateOf(TextFieldValue())}

        Text(text = "Add a box", style = TextStyle(textDecoration = TextDecoration.Underline, fontSize = 40.sp, fontFamily = FontFamily.Cursive))

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
        TextField(
            label = { Text(text = "Boxes available") },
            value = quantity_of_boxes.value,
            onValueChange = { quantity_of_boxes.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        ButtonTemplate(onButtonClick = {onSellBoxClick()}, text = stringResource(id = R.string.sellbox))

    }
}