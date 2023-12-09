package stu.mai.bd_mai.features.listoforders.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import stu.mai.bd_mai.database.entities.Order
import stu.mai.bd_mai.ui.theme.Bd_maiTheme
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckScreen( ) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("List of Orders")

            Spacer(modifier = Modifier.height(16.dp))

            // Список заказов
            LazyColumn {
//                items(viewModel.orders.value) { order ->
//                    OrderListItem(order = order)
                }
            }

            // Кнопка для добавления нового заказа
            IconButton(
                onClick = {
//                    navigateToAddOrder()
                },
                modifier = Modifier
//                    .align(Alignment.End)
                    .padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add Order")
            }
        }


    }

@Composable
fun OrderListItem(order: Order) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { /* Handle click on the order item if needed */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Отобразите информацию о заказе
        Text(text = "Order ID: ${order.ORDER_ID}, Customer ID: ${order.CUSTOMER_ID}, Status: ${order.STATUS_OF_ORDER}")
    }
}



//@Preview(showBackground = true)
//@Composable
//fun OrdersScreenPreview() {
//    Bd_maiTheme {
//        // Пример использования OrdersScreen в предварительном просмотре
//        CheckScreen(
//            viewModel = CheckScreenVM(),
//            navigateToAddOrder = {}
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun OrderListItemPreview() {
    Bd_maiTheme {
        // Пример использования OrderListItem в предварительном просмотре
        OrderListItem(
            order = Order(ORDER_ID = 1, CUSTOMER_ID = 1, EXECUTOR_ID = 1, ORDER_DATE = "2021-10-10", STATUS_OF_ORDER = "Pending")
        )
    }
}

