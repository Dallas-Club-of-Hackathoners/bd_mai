package stu.mai.bd_mai.features.settings.customers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingCustomersScreen (
    viewModel: SettingCustomersVM = hiltViewModel(),
    onNavigateBack: () -> Unit,
    ) {
    // Состояния для текстовых полей и видимости кнопки
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isSaveButtonEnabled by remember { mutableStateOf(false) }

    // Эффект для проверки заполненности обязательного поля
    LaunchedEffect(name) {
        isSaveButtonEnabled = name.isNotBlank()
    }

    // Главный контейнер
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Тулбар
        TopAppBar(
            title = { Text(text = "Добавление заказчиков") },
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

            // Поля ввода
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    isSaveButtonEnabled = it.isNotBlank()
                },
                label = { Text("Имя") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                isError = name.isBlank()
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Телефон") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Эл. почта") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
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
                        viewModel.addCustomer(name, phone,email)
                        onNavigateBack()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    enabled = isSaveButtonEnabled,
                    colors = ButtonDefaults.buttonColors(
                        if (isSaveButtonEnabled) Color.Green else Color.LightGray
                    )
                ) {
                    Text("Сохранить", color = Color.Black)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { onNavigateBack() },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color.Red)
                ) {
                    Text("Отмена", color = Color.Black)
                }
            }
        }

    }

}

@Composable
@Preview(showBackground = true)
fun PreviewAddCustomerScreen() {
    SettingCustomersScreen(
        onNavigateBack = { /*TODO*/ }
    )
}
