package stu.mai.bd_mai

import androidx.compose.runtime.Composable
import stu.mai.bd_mai.navigation.Navigation
import stu.mai.bd_mai.navigation.rememberNavigationState

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Navigation(
        navHostController = navigationState.navHostController,
    )
}
