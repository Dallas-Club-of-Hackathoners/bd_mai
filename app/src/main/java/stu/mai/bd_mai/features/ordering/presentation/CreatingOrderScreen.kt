package stu.mai.bd_mai.features.ordering.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import stu.mai.bd_mai.database.entities.Customer
import stu.mai.bd_mai.database.entities.Executor
import stu.mai.bd_mai.database.entities.Product


@Composable
fun CreatingOrderScreen (
    viewModel: CreatingOrderVM = hiltViewModel(
//        factory = CreatingOrderVM.factory
    ),
    onNavigateBack: () -> Unit,
) {
    var expandedCustomerMenu by remember { mutableStateOf(false) }
    var expandedExecutorMenu by remember { mutableStateOf(false) }
    var expandedProductMenu by remember { mutableStateOf(false) }

    var selectedCustomer by remember { mutableStateOf<Customer?>(null) }
    var selectedExecutor by remember { mutableStateOf<Executor?>(null) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    var count by remember { mutableStateOf(0) }
    var status by remember { mutableStateOf("") }

    // Получить списки заказчиков, исполнителей и продуктов
    val customers by viewModel.getCustomers().collectAsState(initial = emptyList())
    val executors by viewModel.getExecutors().collectAsState(initial = emptyList())
    val products by viewModel.getProducts().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Выбор заказчика
        Column {
            Text(
                text = selectedCustomer?.NAME ?: "Select Customer",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .clickable {
                        expandedCustomerMenu = !expandedCustomerMenu
                    }
                    .padding(16.dp)
            )

            DropdownMenu(
                expanded = expandedCustomerMenu,
                onDismissRequest = { expandedCustomerMenu = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                customers.forEach { customer ->
                    DropdownMenuItem(
                        text = { Text(text = customer.NAME) },
                        onClick = {
                            selectedCustomer = customer
                            expandedCustomerMenu = false
                        }
                    )
                }
            }
        }

        // Выбор исполнителя
        Column {
            Text(
                text = selectedExecutor?.NAME ?: "Select Executor",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .clickable {
                        expandedExecutorMenu = !expandedExecutorMenu
                    }
                    .padding(16.dp)
            )

            DropdownMenu(
                expanded = expandedExecutorMenu,
                onDismissRequest = { expandedExecutorMenu = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                executors.forEach { executor ->
                    DropdownMenuItem(
                        text = { Text(text = executor.NAME) },
                        onClick = {
                            selectedExecutor = executor
                            expandedExecutorMenu = false
                        }
                    )
                }
            }
        }

        // Выбор продукта
        Column {
            Text(
                text = selectedProduct?.NAME ?: "Select Product",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .clickable {
                        expandedProductMenu = !expandedProductMenu
                    }
                    .padding(16.dp)
            )

            DropdownMenu(
                expanded = expandedProductMenu,
                onDismissRequest = { expandedProductMenu = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                products.forEach { product ->
                    DropdownMenuItem(
                        text = { Text(text = product.NAME) },
                        onClick = {
                            selectedProduct = product
                            expandedProductMenu = false
                        }
                    )
                }
            }
        }


        // Ввод количества
        OutlinedTextField(
            value = count.toString(),
            onValueChange = { count = it.toIntOrNull() ?: 0 },
            label = { Text("Count") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
//                    LocalSoftwareKeyboardController.current?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Ввод статуса
        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Кнопки "Сохранить" и "Отмена"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    selectedCustomer?.let { customer ->
                        selectedExecutor?.let { executor ->
                            selectedProduct?.let { product ->
                                viewModel.createOrder(
                                    customerId = customer.CUSTOMER_ID,
                                    executorId = executor.EXECUTOR_ID,
                                    productId = product.PRODUCT_ID,
                                    count = count,
                                    status = status
                                )
                                onNavigateBack()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Save", color = Color.White)
                }
            }

            Button(
                onClick = { onNavigateBack() },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Cancel")
                }
            }
        }
    }

}