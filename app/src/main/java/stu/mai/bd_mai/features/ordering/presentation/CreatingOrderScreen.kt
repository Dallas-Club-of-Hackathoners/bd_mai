package stu.mai.bd_mai.features.ordering.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CreatingOrderScreen () {
    var customerName by remember { mutableStateOf("") }
    var orderStatus by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Поле ввода для имени заказчика
        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Поле ввода для статуса заказа
        OutlinedTextField(
            value = orderStatus,
            onValueChange = { orderStatus = it },
            label = { Text("Order Status") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Кнопка для добавления заказа
        Button(
            onClick = {
                // Создайте экземпляр Order и добавьте его в базу данных
//                val newOrder = Order(customerName = customerName, orderStatus = orderStatus)
//                orderViewModel.insertOrder(newOrder)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Add Order")
        }
    }
}

//Используйте ViewModel для выполнения операции вставки:
//
//kotlin
//
//class OrderViewModel(private val orderDao: OrderDao) : ViewModel() {
//
//    val allOrders: LiveData<List<Order>> = orderDao.getAllOrders().asLiveData()
//
//    fun insertOrder(order: Order) {
//        viewModelScope.launch {
//            orderDao.insertOrder(order)
//        }
//    }
//}