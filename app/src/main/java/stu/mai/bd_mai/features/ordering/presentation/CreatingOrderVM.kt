package stu.mai.bd_mai.features.ordering.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.MaterialsInProduct
import stu.mai.bd_mai.database.entities.MaterialsSuppliers
import stu.mai.bd_mai.database.entities.Order
import stu.mai.bd_mai.database.entities.ProductInOrder
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CreatingOrderVM @Inject constructor(val database: AppDatabase): ViewModel() {

    // Получить список заказчиков из базы данных
    fun getCustomers() = database.getCustomerDao().getAllCustomers()

    // Получить список исполнителей из базы данных
    fun getExecutors() = database.getExecutorDao().getAllExecutors()

    // Получить список продуктов из базы данных
    fun getProducts() = database.getProductDao().getAllProducts()

    fun getMaterials() = database.getMaterialDao().getAllMaterials()

    fun getSuppliers() = database.getSupplierDao().getAllSuppliers()

    // Создать новый заказ
    fun createOrder(
        customerId: Int,
        executorId: Int,
        status: String,
        productId: Int,
        orderCount: Int,
        materialId: Int,
        supplierId: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Вставка заказа
                var order = Order(0, 0, 0, "0", "")
                    val currentDate = Calendar.getInstance().time
                    order = Order(
                        CUSTOMER_ID = customerId,
                        EXECUTOR_ID = executorId,
                        ORDER_DATE = currentDate.toString(),
                        STATUS_OF_ORDER = status
                    )

                database.getOrderDao().insertOrder(order)

                var maxOrderId = 0

                    maxOrderId = database.getOrderDao().getMaxOrderId()


                var productInOrder = ProductInOrder(0, 0, 0)

                     productInOrder = ProductInOrder(
                        ORDER_ID = maxOrderId,
                        PRODUCT_ID = productId,
                        COUNT = orderCount
                    )


                database.getProductInOrderDao().insertProductInOrder(productInOrder)
                // Вставка продукта в заказ

                var materialInProduct = MaterialsInProduct(0, 0, 0)
                     materialInProduct = MaterialsInProduct(
                        MATERIAL_ID = materialId,
                        PRODUCT_ID = productId,
                        COUNT = 1
                    )

                // Вставка материала к продукту
                database.getMaterialsInProductDao().insertMaterialsInProduct(materialInProduct)

                // Вставка поставщика материала
                var materialSupplier = MaterialsSuppliers(0, 0)

                    materialSupplier = MaterialsSuppliers(
                        MATERIAL_ID = materialId,
                        SUPPLIER_ID = supplierId
                    )

                database.getMaterialsSuppliersDao().insertMaterialsSuppliers(materialSupplier)

            } catch (e: Exception) {
                Log.d("1", "Error creating order: ${e.message}")
            }
        }
    }

}