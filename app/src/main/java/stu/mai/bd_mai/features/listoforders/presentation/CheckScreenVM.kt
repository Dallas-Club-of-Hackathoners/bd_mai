package stu.mai.bd_mai.features.listoforders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Customer
import stu.mai.bd_mai.database.entities.Executor


class CheckScreenVM(val database: AppDatabase): ViewModel() {

    val ordersList = database.getOrderDao().getAllOrders()

    fun getCustomerByOrderId(orderId: Int): Customer? {
        var customer: Customer? = null
        viewModelScope.launch {
            val order = database.getOrderDao().getOrderById(orderId)
            val customerId = order?.CUSTOMER_ID

            if (customerId != null) {
                customer = database.getCustomerDao().getCustomerById(customerId)
            }
        }
        return customer
    }

    fun getExecutorByOrderId(orderId: Int): Executor? {
        var executor: Executor? = null
        viewModelScope.launch {
            val order = database.getOrderDao().getOrderById(orderId)
            val executorId = order?.EXECUTOR_ID

            if (executorId != null) {
                executor = database.getExecutorDao().getExecutorById(executorId)
            }
        }
        return executor
    }




    companion object {
        val factory: androidx.lifecycle.ViewModelProvider.Factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return CheckScreenVM(database) as T
            }
        }
    }
}