package stu.mai.bd_mai.features.listoforders.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import stu.mai.bd_mai.database.entities.Customer
import stu.mai.bd_mai.database.entities.Executor
import stu.mai.bd_mai.database.entities.Order

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CheckScreen(
//    navHostController: NavHostController,
    viewModel: CheckScreenVM = viewModel(factory = CheckScreenVM.factory),
    onNavigateToCreatingOrder: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToCardOrder:(id: Int) -> Unit,
) { Column {
    TopAppBar(
        title = {
            Text(text = "Список заказов")
        },
        actions = {
            // Add any actions if needed
        }
    )

//    OrderListContent()

    val itemsList = viewModel.ordersList.collectAsState(initial = emptyList())
    val lazyColumnListState = rememberLazyListState()

    LazyColumn(
        state = lazyColumnListState
    ) {
        items(itemsList.value.size) { index ->
            OrderListItem(
                order = itemsList.value[index],
//                customer = viewModel.getCustomerByOrderId(itemsList.value[index].CUSTOMER_ID)!!,
//                executor = viewModel.getExecutorByOrderId(itemsList.value[index].EXECUTOR_ID!!)!!,
                onItemCLick = { id ->
                    onNavigateToCardOrder(id)
                }
            )
        }
    }

    BottomAppBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Row for placing buttons at the bottom
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Button 1
            IconButton(
                onClick = { onNavigateToCreatingOrder() }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }

            // Button 2
            IconButton(
                onClick = { onNavigateToSettings() }
            ) {
                Icon(imageVector = Icons.Default.Build, contentDescription = "Delete")
            }
        }
}

            }


}

@Composable
fun OrderListItem(
    order: Order,
//    customer: Customer,
//    executor: Executor,
    onItemCLick: (Int) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { onItemCLick(order.ORDER_ID) } // Вызов функции при клике
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Отобразите информацию о заказе
        ClickableText(
            text = AnnotatedString("Заказ №${order.ORDER_ID}\n Дата: ${order.ORDER_DATE}\n Статус: ${order.STATUS_OF_ORDER}"),
            onClick = { offset ->
                // Обработка клика на часть текста, если необходимо
            }
        )
    }
}

//@Composable
//fun OrderListContent(
//        viewModel: CheckScreenVM = viewModel(factory = CheckScreenVM.factory),
//) {
//    val itemsList = viewModel.ordersList.collectAsState(initial = emptyList())
//
//
//    LazyColumn {
//        items(itemsList.value.size) { index ->
//            OrderListItem(
//                order = itemsList.value[index],
//                customer = viewModel.getCustomerByOrderId(itemsList.value[index].ORDER_ID),
//                executor = viewModel.getExecutorByOrderId(itemsList.value[index].ORDER_ID),
//                onItemCLick = { id ->
//                    onNavigateToCardOrder(id)
//                }
//            )
//        }
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun OrderListScreenPreview() {
//    CheckScreen()
//}

//@Preview
//@Composable
//fun OrderListItemPreview() {
//    val order = Order(
//        ORDER_ID = 123,
//        CUSTOMER_ID = 456,
//        EXECUTOR_ID = 789,
//        ORDER_DATE = "2023-01-01",
//        STATUS_OF_ORDER = "Pending"
//    )
//
//    val customer = Customer(
//        CUSTOMER_ID = 456,
//        NAME = "John Doe",
//        PHONE = "123-456-789",
//        EMAIL = "john.doe@example.com"
//    )
//
//    val executor = Executor(
//        EXECUTOR_ID = 789,
//        NAME = "Jane Doe",
//        PHONE = "987-654-321",
//        EMAIL = "jane.doe@example.com"
//    )
//
//    OrderListItem(order = order, customer = customer, executor = executor)
//}


