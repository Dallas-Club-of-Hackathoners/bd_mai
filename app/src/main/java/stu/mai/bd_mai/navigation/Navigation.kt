package stu.mai.bd_mai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Navigation(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.CheckScreen.route
    ) {

        composable(Screen.CheckScreen.route) {

        }
        composable(Screen.CreatingOrderScreen.route) {
        }
        composable(Screen.CardOrderScreen.route) {
        }
    }
}