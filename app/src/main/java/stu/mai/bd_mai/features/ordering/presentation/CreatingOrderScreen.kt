package stu.mai.bd_mai.features.ordering.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import stu.mai.bd_mai.database.entities.Customer
import stu.mai.bd_mai.database.entities.Executor
import stu.mai.bd_mai.database.entities.Material
import stu.mai.bd_mai.database.entities.Product
import stu.mai.bd_mai.database.entities.Supplier


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatingOrderScreen (
    viewModel: CreatingOrderVM = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    var selectedCustomer by remember { mutableStateOf<Customer>(Customer(0,"test","", "")) }
    var selectedExecutor by remember { mutableStateOf<Executor>(Executor(0,"test","", "")) }
    var selectedProduct by remember { mutableStateOf<Product>(Product(0,"test", 23.2, "sdd")) }
    var selectedMaterial by remember { mutableStateOf<Material>(Material(0,"test","ru")) }
    var selectedSupplier by remember { mutableStateOf<Supplier>(Supplier(0,"test","", "")) }

    var count by remember { mutableIntStateOf(0) }
    var orderStatus by remember { mutableStateOf("") }

    // Получить списки заказчиков, исполнителей и продуктов
    val customers by viewModel.getCustomers().collectAsState(initial = emptyList())
    val executors by viewModel.getExecutors().collectAsState(initial = emptyList())
    val products by viewModel.getProducts().collectAsState(initial = emptyList())
    val materials by viewModel.getMaterials().collectAsState(initial = emptyList())
    val suppliers by viewModel.getSuppliers().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Создание заказа") },
                colors = TopAppBarDefaults.topAppBarColors( Color.Cyan),
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                actions = {
                    // Добавляем кнопку "Отмена" справа
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Отмена")
                    }
                },
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                    Spacer(modifier = Modifier.height(36.dp))


                    CustomerDropdown(customers = customers, onCustomerSelected = {selectedCustomer = it} )
                    ExecutorDropdown(executors = executors, onExecutorSelected = {selectedExecutor = it} )
                    ProductDropdown(products = products, onProductSelected = {selectedProduct = it} )
                    MaterialDropdown(materials = materials, onMaterialSelected ={selectedMaterial = it} )
                    SupplierDropdown(suppliers = suppliers, onSupplierSelected = {selectedSupplier = it} )


                // Выпадающие списки

                // Ввод количества
                OutlinedTextField(
                    value = count.toString(),
                    onValueChange = { count = it.toIntOrNull() ?: 0 },
                    label = { Text("Количество") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            // LocalSoftwareKeyboardController.current?.hide()
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                // Ввод статуса
                OutlinedTextField(
                    value = orderStatus,
                    onValueChange = { orderStatus = it },
                    label = { Text("Статус") },
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
                                        selectedMaterial?.let { material ->
                                            selectedSupplier?.let { supplier ->
                                                viewModel.createOrder(
                                                    customerId = customer.CUSTOMER_ID,
                                                    executorId = executor.EXECUTOR_ID,
                                                    status = orderStatus,
                                                    productId = product.PRODUCT_ID,
                                                    orderCount = count,
                                                    materialId = material.MATERIAL_ID,
                                                    supplierId = supplier.SUPPLIER_ID
                                                )
                                                onNavigateBack()
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(5.dp),
                        colors = ButtonDefaults.buttonColors( Color.Cyan)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(imageVector = Icons.Filled.Check, contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Сохранить", color = Color.Black)
                        }
                    }

                    Button(
                        onClick = { onNavigateBack() },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(5.dp),
                        colors = ButtonDefaults.buttonColors( Color.Cyan)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Отмена", color = Color.Black)
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun CustomerDropdown(
    customers: List<Customer>,
    onCustomerSelected: (Customer) -> Unit
) {
    var expandedMenu by remember { mutableStateOf(false) }
    var selectedCustomer by remember { mutableStateOf<Customer?>(null) }

    Column {
        Text(
            text = selectedCustomer?.NAME ?: "Выберите заказчика",
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .clickable {
                    expandedMenu = !expandedMenu
                }
                .padding(16.dp)
        )

        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = { expandedMenu = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            customers.forEach { customer ->
                DropdownMenuItem(
                    text = { Text(text = customer.NAME) },
                    onClick = {
                        selectedCustomer = customer
                        onCustomerSelected(customer)
                        expandedMenu = false
                    }
                )
            }
        }
    }
}

@Composable
fun MaterialDropdown(
    materials: List<Material>,
    onMaterialSelected: (Material) -> Unit
) {
    var expandedMenu by remember { mutableStateOf(false) }
    var selectedMaterial by remember { mutableStateOf<Material?>(null) }

    Column {
        Text(
            text = selectedMaterial?.NAME ?: "Выберите материал",
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .clickable {
                    expandedMenu = !expandedMenu
                }
                .padding(16.dp)
        )

        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = { expandedMenu = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            materials.forEach { material ->
                DropdownMenuItem(
                    text = { Text(text = material.NAME) },
                    onClick = {
                        selectedMaterial = material
                        onMaterialSelected(material)
                        expandedMenu = false
                    }
                )
            }
        }
    }
}
@Composable
fun SupplierDropdown(
    suppliers: List<Supplier>,
    onSupplierSelected: (Supplier) -> Unit
) {
    var expandedMenu by remember { mutableStateOf(false) }
    var selectedSupplier by remember { mutableStateOf<Supplier?>(null) }

    Column {
        Text(
            text = selectedSupplier?.NAME ?: "Выберите поставщика",
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .clickable {
                    expandedMenu = !expandedMenu
                }
                .padding(16.dp)
        )

        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = { expandedMenu = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            suppliers.forEach { supplier ->
                DropdownMenuItem(
                    text = { Text(text = supplier.NAME) },
                    onClick = {
                        selectedSupplier = supplier
                        onSupplierSelected(supplier)
                        expandedMenu = false
                    }
                )
            }
        }
    }
}

@Composable
fun ExecutorDropdown(
    executors: List<Executor>,
    onExecutorSelected: (Executor) -> Unit
) {
    var expandedMenu by remember { mutableStateOf(false) }
    var selectedExecutor by remember { mutableStateOf<Executor?>(null) }

    Column {
        Text(
            text = selectedExecutor?.NAME ?: "Выберите исполнителя",
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .clickable {
                    expandedMenu = !expandedMenu
                }
                .padding(16.dp)
        )

        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = { expandedMenu = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            executors.forEach { executor ->
                DropdownMenuItem(
                    text = { Text(text = executor.NAME) },
                    onClick = {
                        selectedExecutor = executor
                        onExecutorSelected(executor)
                        expandedMenu = false
                    }
                )
            }
        }
    }
}

@Composable
fun ProductDropdown(
    products: List<Product>,
    onProductSelected: (Product) -> Unit
) {
    var expandedMenu by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    Column {
        Text(
            text = selectedProduct?.NAME ?: "Выберите продукт",
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .clickable {
                    expandedMenu = !expandedMenu
                }
                .padding(16.dp)
        )

        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = { expandedMenu = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            products.forEach { product ->
                DropdownMenuItem(
                    text = { Text(text = product.NAME) },
                    onClick = {
                        selectedProduct = product
                        onProductSelected(product)
                        expandedMenu = false
                    }
                )
            }
        }
    }
}
