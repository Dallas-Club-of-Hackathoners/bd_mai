package stu.mai.bd_mai.features.orders.presentation.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import stu.mai.bd_mai.database.entities.Customer
import stu.mai.bd_mai.database.entities.Executor
import stu.mai.bd_mai.database.entities.Supplier
import stu.mai.bd_mai.features.orders.domain.entity.MaterialCore
import stu.mai.bd_mai.features.orders.domain.entity.OrderCore
import stu.mai.bd_mai.features.orders.domain.entity.ProductCore
import stu.mai.bd_mai.use


@Composable
fun CardOrderScreenRoute(
    orderId: Int,
    viewModel: CardOrderVM = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {

    val (state, event) = use(viewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        if (state.order == null) {
            event.invoke(CardOrderContract.Event.OnGetOrder(orderId))
        }
    }

    CardOrder(
        orderState = state,
        onNavigateBack = onNavigateBack,
        viewModel = viewModel,
    )

}

//val LocalCardOrderViewModel = staticCompositionLocalOf<CardOrderVM> {
//    error("No ViewModel provided")
//}
//
//@Composable
//fun ProvideCardOrderViewModel(viewModel: CardOrderVM, content: @Composable () -> Unit) {
//    CompositionLocalProvider(LocalCardOrderViewModel provides viewModel) {
//        content()
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardOrder(
    orderState: CardOrderContract.State,
    onNavigateBack: () -> Unit,
    viewModel: CardOrderVM
) {

    Column {
        TopAppBar(
            title = { Text(text = "Заказ №${orderState.order?.orderId}") },
            actions = {
                IconButton(onClick = {
                    viewModel.deleteOrder(orderState.order?.orderId ?: 0)
                    onNavigateBack()
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Удалить")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(Color.Cyan),
            navigationIcon = {
                IconButton(onClick = { onNavigateBack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Назад")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Дата заказа: ${orderState.order?.orderData}")
            Text("Статус заказа: ${orderState.order?.orderStatus}")

            // Информация об исполнителе
            Text("Исполнитель: ${orderState.order?.orderExecutor?.NAME}")
            // Информация о заказчике
            Text("Заказчик: ${orderState.order?.orderCustomer?.NAME}")

            // Информация о цене заказа
            Text("Цена заказа: ${orderState.order?.orderPrice}")

            // Информация о продуктах
            Text("Продукты:")
            orderState.order?.orderProduct?.forEach { product ->
                ProductCard(product)
            }
        }
    }
}

@Composable
fun ProductCard(product: ProductCore) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("ID продукта: ${product.productId}")
        Text("Название продукта: ${product.productName}")
        Text("Цена продукта: ${product.productPrice}")
        Text("Описание продукта: ${product.productDescription}")

        // Информация о материалах продукта
        Text("Материалы:")
        product.productMaterials.forEach { material ->
            MaterialCard(material)
        }
    }
}

@Composable
fun MaterialCard(material: MaterialCore) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("ID материала: ${material.materialId}")
        Text("Название материала: ${material.materialName}")
        Text("Единица измерения материала: ${material.materialDimension}")

        // Информация о поставщиках материала
        Text("Поставщики:")
        material.materialSuppliers.forEach { supplier ->
            SupplierCard(supplier)
        }
    }
}

@Composable
fun SupplierCard(supplier: Supplier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("ID поставщика: ${supplier.SUPPLIER_ID}")
        Text("Имя поставщика: ${supplier.NAME}")
        Text("Телефон поставщика: ${supplier.PHONE}")
        Text("Email поставщика: ${supplier.EMAIL}")

        // Добавьте дополнительные поля поставщика, если они есть
    }
}

//@Composable
//@Preview(showBackground = true)
//fun CardOrderPreview() {
//    val orderState = CardOrderContract.State(
//        order = OrderCore(
//            orderId = 1,
//            orderData = "2023-01-01",
//            orderStatus = "В обработке",
//            orderExecutor = Executor(1, "Иванов", "", ""),
//            orderCustomer = Customer(1, "Петров","", ""),
//            orderPrice = 100.0,
//            orderProduct = listOf(
//                ProductCore(
//                    1, "Продукт 1", 50.0, "Описание продукта 1",
//                    listOf(MaterialCore(1, "Материал 1", "шт.", emptyList()))
//                )
//            )
//        )
//    )
//    CardOrder(orderState = orderState, onNavigateBack = {})
//}
