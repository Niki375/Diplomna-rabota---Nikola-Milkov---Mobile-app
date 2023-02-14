package diplomna.savemyfood.navigation

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
        route = "CustomerHome",
        icon = Icons.Rounded.Home,
    ),
    BottomNavItem(
        name = "Cart",
        route = "CustomerCart",
        icon = Icons.Rounded.ShoppingCart,
    ),
    BottomNavItem(
        name = "Map",
        route = "CustomerMap",
        icon = Icons.Rounded.LocationOn,
    ),
    BottomNavItem(
        name = "Profile",
        route = "CustomerProfile",
        icon = Icons.Rounded.AccountCircle,
    )
)

val secondbottomNavItems = listOf(
    BottomNavItem(
        name = "Home",
        route = "BusinessHome",
        icon = Icons.Rounded.Home,
    ),
    BottomNavItem(
        name = "Profile",
        route = "BusinessProfile",
        icon = Icons.Rounded.AccountCircle,
    ),
)
