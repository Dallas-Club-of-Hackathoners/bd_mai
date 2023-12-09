package stu.mai.bd_mai.features.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NewParamsScreen (
    //newParamsVM: NewParamsVM = viewModel(factory = NewParamsVM.factory)
    onNavigateToCustomersSettings: () -> Unit,
    onNavigateToExecutorsSettings: () -> Unit,
    onNavigateToMaterialsSettings: () -> Unit,
    onNavigateToProductsSettings: () -> Unit,
    onNavigateToSuppliersSettings: () -> Unit,

) {
    // Задайте голубой цвет

    // Главный контейнер
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(26.dp))
        // Кнопка для навигации к настройкам заказчиков
        Button(
            onClick = { onNavigateToCustomersSettings() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Customers Settings", color = Color.White)
        }

        // Промежуток между кнопками
        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для навигации к настройкам исполнителей
        Button(
            onClick = { onNavigateToExecutorsSettings() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Executors Settings", color = Color.White)
        }

        // Промежуток между кнопками
        Spacer(modifier = Modifier.height(16.dp))

        // Продолжите аналогичным образом для оставшихся трех кнопок

        // Кнопка для навигации к настройкам материалов
        Button(
            onClick = { onNavigateToMaterialsSettings() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Materials Settings", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для навигации к настройкам продуктов
        Button(
            onClick = { onNavigateToProductsSettings() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Products Settings", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для навигации к настройкам поставщиков
        Button(
            onClick = { onNavigateToSuppliersSettings() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Suppliers Settings", color = Color.White)
        }
    }
}