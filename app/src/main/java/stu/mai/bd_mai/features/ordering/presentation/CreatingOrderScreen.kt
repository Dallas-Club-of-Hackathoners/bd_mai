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
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview
@Composable
fun CreatingOrderScreen (
//    orderViewModel: CreatingOrderVM = viewModel(factory = CreatingOrderVM.factory)
) {
    var customerName by remember { mutableStateOf("") }
    var orderStatus by remember { mutableStateOf("") }

    var customerId by remember { mutableStateOf(0) }
    var executorId by remember { mutableStateOf(0) }
    var orderDate by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Введите заказчика") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = orderStatus,
            onValueChange = { orderStatus = it },
            label = { Text("Введите исполнителя") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                //orderViewModel.createOrder(orderId, customerId, executorId, orderDate, status)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Создать заказ")
        }
    }




}