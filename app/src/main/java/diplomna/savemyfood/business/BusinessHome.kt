package diplomna.savemyfood.business

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BusinessHomePage(viewModel: BusinessHomeViewModel, onAddBoxClick: () -> Unit) {
    // Call loadBoxes to get the data
    viewModel.loadBoxes()
    // Use viewModel.boxes to access the data
    val boxes = viewModel.boxes.value

    Column(
        modifier = Modifier.padding(20.dp),
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
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { onAddBoxClick() },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Add a box")
            }
        }

        boxes?.let {
            for (box in it) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = box.food_type)
                }
            }
        }
    }
}


