package com.example.safefood.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(
        name = "Home",
        route = "UserHome",
        icon = Icons.Rounded.Home,
    ),
    BottomNavItem(
        name = "Cart",
        route = "UserCart",
        icon = Icons.Rounded.ShoppingCart,
    ),
    BottomNavItem(
        name = "Profile",
        route = "UserProfile",
        icon = Icons.Rounded.AccountCircle,
    ),
    BottomNavItem(
        name = "Map",
        route = "UserMap",
        icon = Icons.Rounded.LocationOn,
    )
)

val secondbottomNavItems = listOf(
    BottomNavItem(
        name = "Home",
        route = "ShopHome",
        icon = Icons.Rounded.Home,
    ),
    BottomNavItem(
        name = "Profile",
        route = "ShopProfile",
        icon = Icons.Rounded.AccountCircle,
    ),
)
