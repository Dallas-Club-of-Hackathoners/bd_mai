package stu.mai.bd_mai.features.settings.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NewParamsScreen(
        onNavigateToCustomersSettings: () -> Unit,
        onNavigateToExecutorsSettings: () -> Unit,
        onNavigateToMaterialsSettings: () -> Unit,
        onNavigateToProductsSettings: () -> Unit,
        onNavigateToSuppliersSettings: () -> Unit,
        onNavigateBack: () -> Unit
    ) {
        // Главный контейнер
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .background(Color.LightGray)
        ) {
            // Тулбар
            TopAppBar(
                title = { Text(text = "Настройки") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors( Color.Cyan),
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Кнопка для навигации к настройкам заказчиков
                SettingsButton(text = "Заказчики", onClick = onNavigateToCustomersSettings)

                // Кнопка для навигации к настройкам исполнителей
                SettingsButton(text = "Исполнители", onClick = onNavigateToExecutorsSettings)

                // Кнопка для навигации к настройкам материалов
                SettingsButton(text = "Материалы", onClick = onNavigateToMaterialsSettings)

                // Кнопка для навигации к настройкам продуктов
                SettingsButton(text = "Продукты", onClick = onNavigateToProductsSettings)

                // Кнопка для навигации к настройкам поставщиков
                SettingsButton(text = "Поставщики", onClick = onNavigateToSuppliersSettings)
            }
            // Промежуток между тулбаром и кнопками

        }
    }

    @Composable
    fun SettingsButton(text: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors( Color.Cyan)
        ) {
            Text(text = text, color = Color.Black)
        }

        // Промежуток между кнопками
        Spacer(modifier = Modifier.height(16.dp))
    }

@Composable
@Preview(showBackground = true)
fun PreviewNewParamsScreen() {
    NewParamsScreen(
        onNavigateToCustomersSettings = {},
        onNavigateToExecutorsSettings = {},
        onNavigateToMaterialsSettings = {},
        onNavigateToProductsSettings = {},
        onNavigateToSuppliersSettings = {},
        onNavigateBack = {}
    )
}
