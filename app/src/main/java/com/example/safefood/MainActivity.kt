package com.example.safefood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.safefood.navigation.NavHost
import com.example.safefood.ui.theme.SafeFoodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafeFoodTheme {
                val navController = rememberNavController()
                Scaffold(
                    content = { padding ->

                        Box(modifier = Modifier.padding(padding)) {
                            NavHost(navController)
                        }
                    }
                )



            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SafeFoodTheme {
        Greeting("Android")
    }
}