package diplomna.savemyfood.customer

import androidx.compose.foundation.layout.*
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
import diplomna.savemyfood.business.BusinessHomeViewModel

@Composable
fun CustomerHomePage(viewModel: CustomerHomeViewModel) {

    // Call loadBoxes to get the data
    viewModel.loadBoxes()
    // Use viewModel.boxes to access the data
    val boxes = viewModel.boxes.value

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Boxes", style = TextStyle(textAlign = TextAlign.Center, fontSize = 40.sp, textDecoration = TextDecoration.Underline))


        Spacer(modifier = Modifier.height(20.dp))
        boxes?.let {
            for (box in it) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = box.food_type)
                }
            }
        }
    }


}