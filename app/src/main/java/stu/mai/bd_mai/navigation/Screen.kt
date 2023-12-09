package stu.mai.bd_mai.navigation

sealed class Screen(
    val route: String
) {

    object CheckScreen : Screen(ROUTE_CHECK)
    object CreatingOrderScreen : Screen(ROUTE_ORDERING)
    object CardOrderScreen : Screen(ROUTE_CARD)

    companion object {


        const val ROUTE_CHECK = "check_screen"
        const val ROUTE_ORDERING = "creating_order"
        const val ROUTE_CARD = "card_order"
    }
}