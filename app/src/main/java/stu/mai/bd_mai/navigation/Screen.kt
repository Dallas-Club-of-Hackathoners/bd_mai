package stu.mai.bd_mai.navigation

sealed class Screen(
    val route: String
) {

    data object CheckScreenRoute : Screen(ROUTE_CHECK)
    data object CreatingOrderScreen : Screen(ROUTE_ORDERING)
    data object NewParamsScreen : Screen(ROUTE_NEW_PARAMS)
    data object SettingSuppliersScreen : Screen(SETTING_SUPPLIERS)
    data object SettingExecutorsScreen : Screen(SETTING_EXECUTORS)
    data object SettingCustomersScreen : Screen(SETTING_CUSTOMERS)
    data object SettingProductsScreen : Screen(SETTING_PRODUCTS)
    data object SettingMaterialsScreen : Screen(SETTING_MATERIALS)

    data object Collection1Screen : Screen(COLLECTION_1)
    data object Collection2Screen : Screen(COLLECTION_2)
    data object Collection3Screen : Screen(COLLECTION_3)
    data object Collection4Screen : Screen(COLLECTION_4)
    data object Collection5Screen : Screen(COLLECTION_5)




    data class CardOrderScreenRoute(val orderId: Int) : Screen(ROUTE_CARD) {
        fun getRouteWithArgs(): String {
            return "$ROUTE_CARD_FOR_ARG/$orderId"
        }

    }

    companion object {

        const val KEY_ID = "order_id"

        const val SETTING_SUPPLIERS = "suppliers"
        const val SETTING_EXECUTORS = "executors"
        const val SETTING_CUSTOMERS = "customers"
        const val SETTING_PRODUCTS = "products"
        const val SETTING_MATERIALS = "materials"
        const val ROUTE_NEW_PARAMS = "new_params"

        const val ROUTE_ORDERING = "creating_order"
        const val ROUTE_CARD_FOR_ARG = "card_order"
        const val ROUTE_CHECK = "check_screen_route"

        const val COLLECTION_1 = "collection_1"
        const val COLLECTION_2 = "collection_2"
        const val COLLECTION_3 = "collection_3"
        const val COLLECTION_4 = "collection_4"
        const val COLLECTION_5 = "collection_5"

        const val ROUTE_CARD = "$ROUTE_CARD_FOR_ARG/{$KEY_ID}"
    }
}