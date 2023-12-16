package stu.mai.bd_mai.features.settings.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingProductsScreen(
    viewModel: SettingProductsVM = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // Парсинг цены в Double
    var parsedPrice by remember { mutableStateOf<Double?>(null) }

    // Состояние для активации кнопки "Сохранить"
    var isSaveButtonEnabled by remember { mutableStateOf(false) }

    // Эффект для обновления состояния кнопки "Сохранить" при изменении полей
    LaunchedEffect(name, parsedPrice, description) {
        isSaveButtonEnabled = name.isNotBlank() && parsedPrice != null && description.isNotBlank()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Тулбар
        TopAppBar(
            title = { Text(text = "Добавление продуктов") },
            colors = TopAppBarDefaults.topAppBarColors( Color.Cyan),
            navigationIcon = {
                IconButton(onClick = { onNavigateBack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Назад")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

// Имя продукта
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    isSaveButtonEnabled = it.isNotBlank() && parsedPrice != null && description.isNotBlank()
                },
                label = {
                    Text("Название продукта")
                },
                placeholder = {
                    Text("Введите название продукта")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = name.isBlank()
            )

// Цена продукта
            OutlinedTextField(
                value = price,
                onValueChange = {
                    price = it
                    // Попробуем парсить введенное значение в Double
                    parsedPrice = it.toDoubleOrNull()
                    isSaveButtonEnabled = name.isNotBlank() && parsedPrice != null && description.isNotBlank()
                },
                label = {
                    Text("Цена продукта")
                },
                placeholder = {
                    Text("Введите цену продукта")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = price.isBlank() || parsedPrice == null
            )

// Описание продукта
            OutlinedTextField(
                value = description,
                onValueChange = {
                    description = it
                    isSaveButtonEnabled = name.isNotBlank() && parsedPrice != null && it.isNotBlank()
                },
                label = {
                    Text("Описание продукта")
                },
                placeholder = {
                    Text("Введите описание продукта")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = description.isBlank()
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
                        // Обработка нажатия кнопки "Сохранить"
                        viewModel.addProduct(name, parsedPrice!!, description)
                        onNavigateBack()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    enabled = isSaveButtonEnabled,
                    colors = ButtonDefaults.buttonColors(
                        if (isSaveButtonEnabled) Color.Green else Color.LightGray,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Сохранить", color = Color.Black)
                }

                Button(
                    onClick = { onNavigateBack() },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text("Отмена", color = Color.Black)
                }
            }
        }
    }
}

