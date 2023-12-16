package stu.mai.bd_mai.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import stu.mai.bd_mai.features.settings.customers.SettingCustomersScreen
import stu.mai.bd_mai.features.settings.executors.SettingExecutorsScreen
import stu.mai.bd_mai.features.orders.presentation.card.CardOrderScreenRoute
import stu.mai.bd_mai.features.orders.presentation.list.CheckScreenRoute
import stu.mai.bd_mai.features.settings.materials.SettingMaterialsScreen
import stu.mai.bd_mai.features.ordering.presentation.CreatingOrderScreen
import stu.mai.bd_mai.features.settings.products.SettingProductsScreen
import stu.mai.bd_mai.features.settings.main.NewParamsScreen
import stu.mai.bd_mai.features.settings.suppliers.SettingSuppliersScreen


@Composable
fun Navigation(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.CheckScreenRoute.route
    ) {

        composable(Screen.CheckScreenRoute.route) {
            CheckScreenRoute(
                onNavigateToSettings = {
                    navHostController.navigate(route = Screen.NewParamsScreen.route)
                    Log.d("Navigation", "Navigating to Settings screen")
                },
                onNavigateToCreatingOrder = {
                    navHostController.navigate(route = Screen.CreatingOrderScreen.route)
                    Log.d("Navigation", "Navigating to Creating Order screen")
                },
                onNavigateToCardOrder = { id ->
                    navHostController.navigate(route = Screen.CardOrderScreen(id).getRouteWithArgs())
                    Log.d("Navigation", "Navigating to Card Order screen with ID: $id")
                }
            )
        }
        composable(Screen.CreatingOrderScreen.route) {
            CreatingOrderScreen(
                onNavigateBack = {
                    navHostController.popBackStack()
                },
            )
        }

        composable(Screen.NewParamsScreen.route) {
            NewParamsScreen(
                onNavigateToCustomersSettings = {
                    navHostController.navigate(route = Screen.SettingCustomersScreen.route)
                },
                onNavigateToExecutorsSettings = {
                    navHostController.navigate(route = Screen.SettingExecutorsScreen.route)
                },
                onNavigateToMaterialsSettings = {
                    navHostController.navigate(route = Screen.SettingMaterialsScreen.route)
                },
                onNavigateToProductsSettings = {
                    navHostController.navigate(route = Screen.SettingProductsScreen.route)
                },
                onNavigateToSuppliersSettings = {
                    navHostController.navigate(route = Screen.SettingSuppliersScreen.route)
                },
                onNavigateBack = {
                    navHostController.popBackStack()
                },
            )

        }
        composable(
            route = Screen.ROUTE_CARD,
            arguments = listOf(navArgument(Screen.KEY_ID) {type = NavType.IntType }) ,
        ) {
            CardOrderScreenRoute(
                orderId = it.arguments?.getInt(Screen.KEY_ID) ?: throw (Exception()),
                onNavigateBack = {
                    navHostController.popBackStack()
                },
            )
        }

        composable(Screen.SettingCustomersScreen.route) {
            SettingCustomersScreen(
                onNavigateBack = {
                    navHostController.popBackStack()
                },
            )
        }
        composable(Screen.SettingExecutorsScreen.route) {
            SettingExecutorsScreen(
                onNavigateBack = {
                    navHostController.popBackStack()
                },
            )
        }
        composable(Screen.SettingMaterialsScreen.route) {
            SettingMaterialsScreen(
                onNavigateBack = {
                    navHostController.popBackStack()
                },
            )
        }
        composable(Screen.SettingProductsScreen.route) {
            SettingProductsScreen(
                onNavigateBack = {
                    navHostController.popBackStack()
                },
            )
        }
        composable(Screen.SettingSuppliersScreen.route) {
            SettingSuppliersScreen(
                onNavigateBack = {
                    navHostController.popBackStack()
                },
            )
        }

    }
}



