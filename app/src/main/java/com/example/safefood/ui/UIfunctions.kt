import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonTemplate(onButtonClick:() -> Unit, text: String){
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
        Button(
            onClick = {onButtonClick()},
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = text)
        }
    }
}