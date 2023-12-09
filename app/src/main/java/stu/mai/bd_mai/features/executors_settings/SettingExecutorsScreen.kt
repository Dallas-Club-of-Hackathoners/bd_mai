package stu.mai.bd_mai.features.executors_settings

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingExecutorsScreen(
    viewModel: SettingExecutorsVM = viewModel(factory = SettingExecutorsVM.factory),
    onNavigateToSettings: () -> Unit,

    ) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Имя исполнителя
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Телефон исполнителя
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
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
            label = { Text("Email") },
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
                    viewModel.addExecutor(name, phone, email)
                    onNavigateToSettings()
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
                onClick = { onNavigateToSettings() },
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