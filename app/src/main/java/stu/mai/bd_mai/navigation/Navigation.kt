package stu.mai.bd_mai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import stu.mai.bd_mai.features.CardOrderScreen
import stu.mai.bd_mai.features.CheckScreen
import stu.mai.bd_mai.features.CreatingOrderScreen
import stu.mai.bd_mai.features.NewParamsScreen

sealed class NavRoutes(val route: String) {
    object NewParams : NavRoutes("new_params")
    object CheckScreen : NavRoutes("check_screen")
    object CreatingOrder : NavRoutes("creating_order")
    object CardOrder : NavRoutes("card_order")
}

@Composable
fun Navigation(navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.CheckScreen.route
    ) {
        composable(NavRoutes.NewParams.route) {
        }
        composable(NavRoutes.CheckScreen.route) {
        }
        composable(NavRoutes.CreatingOrder.route) {
        }
        composable(NavRoutes.CardOrder.route) {
        }
    }
}