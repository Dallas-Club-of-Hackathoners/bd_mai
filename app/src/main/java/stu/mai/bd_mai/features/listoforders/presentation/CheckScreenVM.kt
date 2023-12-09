package stu.mai.bd_mai.features.listoforders.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Order
import javax.inject.Inject








//@HiltViewModel
//class CheckScreenVM @Inject constructor():ViewModel() {
//
//
//    init {
//        // Используйте LaunchedEffect для асинхронного выполнения операции загрузки
//        viewModelScope.launch {
//            // Получите LiveData из базы данных
//
//
//        }
//    }
//}
