package stu.mai.bd_mai.features.orders.presentation.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import stu.mai.bd_mai.database.entities.Customer
import stu.mai.bd_mai.database.entities.Executor
import stu.mai.bd_mai.features.orders.domain.entity.OrderCore
import stu.mai.bd_mai.features.orders.domain.entity.ProductCore
import stu.mai.bd_mai.use

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CheckScreenRoute(
    viewModel: CheckScreenVM = hiltViewModel(
    ),
    onNavigateToCreatingOrder: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToCardOrder: (orderId: Int) -> Unit,
) {

    val (state, event) = use(viewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        if (viewModel.state.value.orderList == emptyFlow<List<OrderCore>>()) {
            event.invoke(CheckScreenContract.Event.OnGetOrderList)
        }
    }

    CheckScreen(
        orderListState = state,
        onNavigateToCreatingOrder = onNavigateToCreatingOrder,
        onNavigateToSettings = onNavigateToSettings,
        onNavigateToCardOrder = onNavigateToCardOrder,
    )

}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckScreen(
    orderListState: CheckScreenContract.State,
    onNavigateToCreatingOrder: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToCardOrder: (orderId: Int) -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                repeat(5) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                // Handle button click here
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        Text("Button ${index + 1}")
                    }
                }
            }
        }
    ) {
        // Content of the screen
        val orderList = orderListState.orderList.collectAsState(initial = emptyList()).value

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Список заказов") },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Cyan
                    ),
                    actions = {
                        // Добавьте кнопки или другие элементы управления, если необходимо
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Cyan, // Задайте цвет фона навигационной панели
                    content = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Здесь разместите ваши иконки для навигационной панели
                            IconButton(
                                onClick = { onNavigateToCreatingOrder() }
                            ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Кнопка 1")
                            }
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } }
                            ) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = "Кнопка 2")
                            }
                            IconButton(
                                onClick = { onNavigateToSettings() }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Кнопка 3"
                                )
                            }
                        }
                    }
                )
            }
        ) {
            // Здесь размещайте остальной код CheckScreen
            // Используем Box для управления компоновкой оставшегося места экрана


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp, bottom = 70.dp, start = 10.dp, end = 10.dp)
            ) {
                LazyColumn {
                    items(orderList) { order ->
                        // Здесь вы можете размещать элементы вашего списка
                        OrderCardSummary(order = order, onNavigateToCardOrder)
                    }
                }
            }
        }
    }
}


@Composable
fun OrderCardSummary(order: OrderCore, onNavigateToCardOrder: (orderId: Int) -> Unit) {
    // Здесь разместите компоненты для отображения карточки заказа
    // Пример:
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Order ID: ${order.orderId}")
            Text(text = "Status: ${order.orderStatus}")
            Text(text = "Executor: ${order.orderExecutor.NAME}")
            Text(text = "Order Price: ${order.orderPrice}")
            Text(text = "Products: ${getProductsSummary(order.orderProduct)}")

            Button(
                onClick = { onNavigateToCardOrder(order.orderId) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
            ) {
                Text(text = "Подробнее")
            }
        }
    }
}


@Composable
@Preview
fun CheckScreenPreview() {
    val orderList = listOf(
        OrderCore(
            orderId = 1,
            orderData = "2023-01-01",
            orderStatus = "В обработке",
            orderExecutor = Executor(EXECUTOR_ID = 1, NAME = "John Doe", PHONE = "123-456-789", EMAIL = "john@example.com"),
            orderCustomer = Customer(CUSTOMER_ID = 2, NAME = "Jane Doe", PHONE = "987-654-321", EMAIL = "jane@example.com"),
            orderPrice = 100.0,
            orderProduct = listOf(
                ProductCore(
                    productId = 1,
                    productName = "Product 1",
                    productPrice = 50.0,
                    productDescription = "Description 1",
                    productMaterials = emptyList()
                ),
                ProductCore(
                    productId = 2,
                    productName = "Product 2",
                    productPrice = 50.0,
                    productDescription = "Description 2",
                    productMaterials = emptyList()
                )
            )
        )
    )

    val previewState = (CheckScreenContract.State(orderList = flowOf(orderList) ))
    CheckScreen(
        orderListState = previewState,
        onNavigateToCreatingOrder = {},
        onNavigateToSettings = {},
        onNavigateToCardOrder = {}
    )
}

@Composable
@Preview
fun OrderCardSummaryPreview() {
    val order = OrderCore(
        orderId = 1,
        orderData = "2023-01-01",
        orderStatus = "В обработке",
        orderExecutor = Executor(EXECUTOR_ID = 1, NAME = "John Doe", PHONE = "123-456-789", EMAIL = "john@example.com"),
        orderCustomer = Customer(CUSTOMER_ID = 2, NAME = "Jane Doe", PHONE = "987-654-321", EMAIL = "jane@example.com"),
        orderPrice = 100.0,
        orderProduct = listOf(
            ProductCore(
                productId = 1,
                productName = "Product 1",
                productPrice = 50.0,
                productDescription = "Description 1",
                productMaterials = emptyList()
            ),
            ProductCore(
                productId = 2,
                productName = "Product 2",
                productPrice = 50.0,
                productDescription = "Description 2",
                productMaterials = emptyList()
            )
        )
    )

    OrderCardSummary(order = order, onNavigateToCardOrder = {})
}



// Вспомогательная функция для получения краткого описания продуктов
@Composable
fun getProductsSummary(products: List<ProductCore>): String {
    val summary = StringBuilder()
    val maxProductsToShow = 2 // Ограничим количество отображаемых продуктов

    for ((index, product) in products.withIndex()) {
        summary.append("${product.productName}, ")
        if (index == maxProductsToShow - 1) {
            break
        }
    }

    // Удалить последнюю запятую и добавить троеточие, если есть еще продукты
    if (products.size > maxProductsToShow) {
        summary.delete(summary.length - 2, summary.length)
        summary.append("...")
    } else {
        summary.deleteCharAt(summary.length - 2)
    }

    return summary.toString()
}
