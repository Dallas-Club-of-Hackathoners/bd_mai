package stu.mai.bd_mai.navigation

sealed class Screen(
    val route: String
) {


    object CheckScreen : Screen(ROUTE_CHECK)
    object CreatingOrderScreen : Screen(ROUTE_ORDERING)
    object NewParamsScreen : Screen(ROUTE_NEW_PARAMS)

    object SettingSuppliersScreen : Screen(SETTING_SUPPLIERS)
    object SettingExecutorsScreen : Screen(SETTING_EXECUTORS)
    object SettingCustomersScreen : Screen(SETTING_CUSTOMERS)
    object SettingProductsScreen : Screen(SETTING_PRODUCTS)
    object SettingMaterialsScreen : Screen(SETTING_MATERIALS)
    data class CardOrderScreen(val id: Int) : Screen(ROUTE_CARD) {
        fun getRouteWithArgs(): String {
            return "$ROUTE_CARD_FOR_ARG/$id"
        }

    }

    companion object {

        const val KEY_ID = "order_id"
        const val SETTING_SUPPLIERS = "suppliers"
        const val SETTING_EXECUTORS = "executors"
        const val SETTING_CUSTOMERS = "customers"
        const val SETTING_PRODUCTS = "products"
        const val SETTING_MATERIALS = "materials"
        const val ROUTE_CHECK = "check_screen"
        const val ROUTE_ORDERING = "creating_order"
        const val ROUTE_CARD_FOR_ARG = "card_order"
        const val ROUTE_CARD = "$ROUTE_CARD_FOR_ARG/{$KEY_ID}"
        const val ROUTE_NEW_PARAMS = "new_params"
    }
}