package diplomna.savemyfood.business

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diplomna.savemyfood.R
import diplomna.savemyfood.ui.theme.ButtonTemplate


@Composable
fun BusinessHomePage(viewModel: BusinessHomeViewModel, onAddBoxClick: () -> Unit) {
    viewModel.loadBoxes()
    viewModel.createBoxEmailMap()
    val boxes = viewModel.boxes.value

    Column(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Boxes",
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
        )

        Spacer(modifier = Modifier.height(20.dp))
        ButtonTemplate(onButtonClick = {onAddBoxClick()}, text = stringResource(id = R.string.add_box))


        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(boxes ?: emptyList()) { box ->
                val boughtBox = Color.Red.copy(alpha = 0.7f)
                val notBoughtBox = Color.Green.copy(alpha = 0.7f)
                var color = notBoughtBox
                println(box.bought)
                if (box.bought) {
                    color = boughtBox
                }

                var email = ""
                if(viewModel.boxEmailMap[box.id] != null) {
                    email = viewModel.boxEmailMap[box.id]!!
                }

                Box(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(color, RoundedCornerShape(16.dp))
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Food type: " + box.food_type)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Description: " + box.description)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Price: " + box.price_per_box + " BGN")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Date to pick up: " + box.pickup_time)
                        Spacer(modifier = Modifier.height(16.dp))
                        if(email != "") {
                            Text(text = "Customer Email: $email")
                        }
                    }
                }
            }
        }
    }
}


