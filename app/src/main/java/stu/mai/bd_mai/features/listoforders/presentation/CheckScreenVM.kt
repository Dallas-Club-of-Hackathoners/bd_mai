package stu.mai.bd_mai.features.listoforders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Customer
import stu.mai.bd_mai.database.entities.Executor
import stu.mai.bd_mai.database.entities.Product
import javax.inject.Inject

@HiltViewModel
class CheckScreenVM @Inject constructor(val database: AppDatabase): ViewModel() {

    val ordersList = database.getOrderDao().getAllOrders()


    private var customer: Customer? = null

    //...
    suspend fun getCustomerByOrderIdAndAssign(orderId: Int) {
        customer = viewModelScope.async(Dispatchers.IO) {
            val order = database.getOrderDao().getOrderById(orderId)
            val customerId = order?.CUSTOMER_ID

            if (customerId != null) {
                return@async database.getCustomerDao().getCustomerById(customerId)
            } else {
                return@async null
            }
        }.await()
    }

    fun getCustomerValue(): Customer? {
        return customer
    }

    private var product: Product =
        Product(0, "", 0.0, "", 0)


//    suspend fun getProductByOrderIdAndAssign(orderId: Int) {
//        product = viewModelScope.async(Dispatchers.IO) {
//            val order = database.getOrderDao().getOrderById(orderId)
//            val productId = order?.PRODUCT_ID
//            if (productId != null) {
//                return@async database.getProductDao().getProductById(productId)
//            } else {
//                return@async Product(0, "", 0.0, "", 0)
//            }
//        }.await()
//    }

    suspend fun getProductAsync(orderId: Int) {
        product = withContext(Dispatchers.IO) {
            val order = database.getOrderDao().getOrderById(orderId)
            val productId = order?.PRODUCT_ID
            if (productId!=null) {
                return@withContext database.getProductDao().getProductById(productId)
            } else
                return@withContext Product(0, "", 0.0, "", 0)
        }
    }
    fun getProductValue(): Product {
        return product
    }




    private var executor: Executor? = null

    //...

    suspend fun getExecutorByOrderIdAndAssign(orderId: Int) {
        executor = viewModelScope.async(Dispatchers.IO) {
            val order = database.getOrderDao().getOrderById(orderId)
            val executorId = order?.EXECUTOR_ID

            if (executorId != null) {
                return@async database.getExecutorDao().getExecutorById(executorId)
            } else {
                return@async null
            }
        }.await()
    }

    fun getExecutorValue(): Executor? {
        return executor
    }

    fun deleteOrder(orderId: Int) {
        viewModelScope.launch {
            database.getOrderDao().deleteOrderById(orderId)
        }
    }


//    companion object {
//        val factory: androidx.lifecycle.ViewModelProvider.Factory = object : androidx.lifecycle.ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
//                return CheckScreenVM(database) as T
//            }
//        }
//    }
}