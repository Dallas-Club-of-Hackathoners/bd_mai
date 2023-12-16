package stu.mai.bd_mai.features.settings.suppliers

import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingSuppliersScreen (
    viewModel: SettingSuppliersVM = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var isSaveButtonEnabled by remember { mutableStateOf(false) }

    // Эффект для проверки заполненности обязательного поля
    LaunchedEffect(name) {
        isSaveButtonEnabled = name.isNotBlank()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Тулбар
        TopAppBar(
            title = { Text(text = "Добавление исполнителей") },
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

            // Имя исполнителя
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    isSaveButtonEnabled = it.isNotBlank()
                },
                label = { Text("Имя") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = name.isBlank()
            )

            // Телефон исполнителя
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Телефон") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* Handle done button click if needed */ }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            // Email исполнителя
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Эл. почта") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Скрыть клавиатуру при нажатии на кнопку "Готово"
//                    LocalSoftwareKeyboardController.current?.hide()
                    }
                ),
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
                        viewModel.addSupplier(name, phone, email)
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
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors( Color.Red)
                ) {
                    Text("Отмена", color = Color.Black)
                }
            }
        }

    }

}