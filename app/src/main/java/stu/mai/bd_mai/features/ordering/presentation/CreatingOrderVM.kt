package stu.mai.bd_mai.features.ordering.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Order
import stu.mai.bd_mai.features.settings.presentation.NewParamsVM
import javax.inject.Inject

class CreatingOrderVM( val database: AppDatabase): ViewModel() {

    fun createOrder(customerId: Int, executorId: Int, orderDate: String, status: String) {
        viewModelScope.launch {
            val order = Order( CUSTOMER_ID =  customerId, EXECUTOR_ID = executorId,ORDER_DATE = orderDate, STATUS_OF_ORDER = status)
            if (database.getOrderDao().getOrderById(order.ORDER_ID) != null) {
                database.getOrderDao().updateOrder(order)
            } else {
                database.getOrderDao().insertOrder(order)
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val database = (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).database
                return CreatingOrderVM(database) as T
            }
        }
    }}