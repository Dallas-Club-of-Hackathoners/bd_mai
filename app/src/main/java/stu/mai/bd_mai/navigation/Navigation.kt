package stu.mai.bd_mai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import stu.mai.bd_mai.features.customers_settings.SettingCustomersScreen
import stu.mai.bd_mai.features.executors_settings.SettingExecutorsScreen
import stu.mai.bd_mai.features.infocard.presentation.CardOrderScreen
import stu.mai.bd_mai.features.listoforders.presentation.CheckScreen
import stu.mai.bd_mai.features.materials_settings.SettingMaterialsScreen
import stu.mai.bd_mai.features.ordering.presentation.CreatingOrderScreen
import stu.mai.bd_mai.features.products_settings.SettingProductsScreen
import stu.mai.bd_mai.features.settings.presentation.NewParamsScreen
import stu.mai.bd_mai.features.suppliers_settings.SettingSuppliersScreen


@Composable
fun Navigation(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.CheckScreen.route
    ) {

        composable(Screen.CheckScreen.route) {
            CheckScreen(
                onNavigateToSettings = {
                    navHostController.navigate(route = Screen.NewParamsScreen.route)
                },
                onNavigateToCreatingOrder = {
                    navHostController.navigate(route = Screen.CreatingOrderScreen.route)
                },

                onNavigateToCardOrder = { id ->
                    navHostController.navigate(route = Screen.CardOrderScreen(id).route)
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
            )

        }
        composable(
            route = Screen.ROUTE_CARD,
            arguments = listOf(navArgument(Screen.KEY_ID) {type = NavType.IntType }) ,
        ) {
            CardOrderScreen(
                orderId = it.arguments?.getInt(Screen.KEY_ID) ?: throw (Exception())
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



