package stu.mai.bd_mai

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import stu.mai.bd_mai.navigation.Navigation
import stu.mai.bd_mai.navigation.rememberNavigationState

@Composable
fun MainScreen() {

    // val navigationState = rememberNavigationState()
    val navController = rememberNavController()
    Navigation(
        navHostController = navController,
    )
}
