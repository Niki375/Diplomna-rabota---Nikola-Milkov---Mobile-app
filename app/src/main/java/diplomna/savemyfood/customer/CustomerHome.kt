package diplomna.savemyfood.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diplomna.savemyfood.business.BusinessHomeViewModel

@Composable
fun CustomerHomePage(viewModel: CustomerHomeViewModel) {

    viewModel.loadBoxes()
    val boxes = viewModel.boxes.value

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Boxes", style = TextStyle(textAlign = TextAlign.Center, fontSize = 40.sp,
            textDecoration = TextDecoration.Underline))


        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(boxes ?: emptyList()) { box ->
                Box(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.Gray.copy(alpha = 0.7f), RoundedCornerShape(16.dp))
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
                        Text(text = "Address: " + box.address)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Business email: " + box.email)
                        Spacer(modifier = Modifier.height(16.dp))
                        var buttonClicked by remember { mutableStateOf(false) }
                        if (!buttonClicked) {
                            Button(onClick = {
                                viewModel.buyBox(box)
                                buttonClicked = true
                            }) {
                                Text(text = "Buy")
                            }
                        }
                    }
                }
            }
        }
    }
}