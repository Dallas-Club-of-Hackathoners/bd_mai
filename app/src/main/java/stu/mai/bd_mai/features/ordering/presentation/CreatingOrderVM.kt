package stu.mai.bd_mai.features.ordering.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Order
import java.util.Calendar

class CreatingOrderVM( val database: AppDatabase): ViewModel() {

    // Получить список заказчиков из базы данных
    fun getCustomers() = database.getCustomerDao().getAllCustomers()

    // Получить список исполнителей из базы данных
    fun getExecutors() = database.getExecutorDao().getAllExecutors()

    // Получить список продуктов из базы данных
    fun getProducts() = database.getProductDao().getAllProducts()

    // Создать новый заказ
    fun createOrder(
        customerId: Int,
        executorId: Int,
        productId: Int,
        count: Int,
        status: String
    ) {
        viewModelScope.launch {
            val currentDate = Calendar.getInstance().time
            val order = Order(
                CUSTOMER_ID = customerId,
                EXECUTOR_ID = executorId,
                PRODUCT_ID = productId,
                COUNT = count,
                ORDER_DATE = currentDate.toString(),
                STATUS_OF_ORDER = status
            )
            database.getOrderDao().insertOrder(order)
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