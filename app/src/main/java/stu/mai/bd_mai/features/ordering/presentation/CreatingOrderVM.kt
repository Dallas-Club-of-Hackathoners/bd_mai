package stu.mai.bd_mai.features.ordering.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.MaterialsInProduct
import stu.mai.bd_mai.database.entities.MaterialsSuppliers
import stu.mai.bd_mai.database.entities.Order
import stu.mai.bd_mai.database.entities.Product
import stu.mai.bd_mai.database.entities.ProductInOrder
import stu.mai.bd_mai.database.entities.Supplier
import stu.mai.bd_mai.features.orders.domain.entity.MaterialCore
import stu.mai.bd_mai.features.orders.domain.entity.ProductCore
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
        productCount: Int,
        materialId: Int,
        supplierId: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Вставка заказа
                val currentDate = Calendar.getInstance().time
                val order = Order(
                    CUSTOMER_ID = customerId,
                    EXECUTOR_ID = executorId,
                    ORDER_DATE = currentDate.toString(),
                    STATUS_OF_ORDER = status
                )
                database.getOrderDao().insertOrder(order)

                val maxOrderId = database.getOrderDao().getMaxOrderId()

                // Вставка продукта в заказ
                val productInOrder = ProductInOrder(
                    ORDER_ID = maxOrderId,
                    PRODUCT_ID = productId,
                    COUNT = productCount
                )
                database.getProductInOrderDao().insertProductInOrder(productInOrder)

                // Вставка материала к продукту
                val materialInProduct = MaterialsInProduct(
                    MATERIAL_ID = materialId,
                    PRODUCT_ID = productId,
                    COUNT = 1
                )
                database.getMaterialsInProductDao().insertMaterialsInProduct(materialInProduct)

                // Вставка поставщика материала
                val materialSupplier = MaterialsSuppliers(
                    MATERIAL_ID = materialId,
                    SUPPLIER_ID = supplierId
                )
                database.getMaterialsSuppliersDao().insertMaterialsSuppliers(materialSupplier)

            } catch (e: Exception) {
                // Обработка ошибки, если необходимо
            }
        }
    }



}