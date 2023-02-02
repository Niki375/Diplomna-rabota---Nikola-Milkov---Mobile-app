package com.example.safefood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.safefood.navigation.NavHost
import com.example.safefood.navigation.bottomNavItems
import com.example.safefood.navigation.secondbottomNavItems
import com.example.safefood.ui.theme.SafeFoodTheme



class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafeFoodTheme {
                val navController = rememberNavController()

                val backStackEntry = navController.currentBackStackEntryAsState()

                Scaffold(
                    content = { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            NavHost(navController)
                        }
                    },

                    bottomBar = {
                        if (shouldShowNavigationBar(backStackEntry.value?.destination?.route)) {
                            androidx.compose.material3.NavigationBar(
                            ) {
                                bottomNavItems.forEach { item ->
                                    val selected =
                                        item.route == backStackEntry.value?.destination?.route

                                    NavigationBarItem(
                                        selected = selected,
                                        onClick = { navController.navigate(item.route) },
                                        label = {
                                            Text(
                                                text = item.name,
                                                fontWeight = FontWeight.SemiBold,
                                            )
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = item.icon,
                                                contentDescription = "${item.name} Icon",
                                            )
                                        }

                                    )
                                }
                            }
                        }
                        if (shouldShowSecondNavBar(backStackEntry.value?.destination?.route)) {
                            androidx.compose.material3.NavigationBar(
                            ) {
                                secondbottomNavItems.forEach { item ->
                                    val selected =
                                        item.route == backStackEntry.value?.destination?.route

                                    NavigationBarItem(
                                        selected = selected,
                                        onClick = { navController.navigate(item.route) },
                                        label = {
                                            Text(
                                                text = item.name,
                                                fontWeight = FontWeight.SemiBold,
                                            )
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = item.icon,
                                                contentDescription = "${item.name} Icon",
                                            )
                                        }

                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    private fun shouldShowNavigationBar(currentRoute: String?): Boolean {
        // Add logic to decide which pages should have the navigation bar and which not
        return  currentRoute == "UserHome" || currentRoute == "UserCart" || currentRoute == "UserProfile" || currentRoute == "UserMap"
    }

    private fun shouldShowSecondNavBar(currentRoute: String?): Boolean {
        // Add logic to decide which pages should have the second navigation bar and which not
        return currentRoute == "Login"
    }
}