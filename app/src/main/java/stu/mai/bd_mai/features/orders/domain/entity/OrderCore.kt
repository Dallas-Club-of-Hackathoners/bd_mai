package stu.mai.bd_mai.features.orders.domain.entity

import stu.mai.bd_mai.database.entities.Customer
import stu.mai.bd_mai.database.entities.Executor
import stu.mai.bd_mai.database.entities.Supplier

data class OrderCore(
    val orderId: Int,
    val orderData: String,
    val orderStatus: String,
    val orderExecutor: Executor,
    val orderCustomer: Customer,
    val orderPrice: Double,
    val orderProduct: List<ProductCore>,

    )

data class ProductCore(
    val productId: Int,
    val productName: String,
    val productPrice: Double,
    val productDescription: String,
    val productMaterials: List<MaterialCore>,
)

data class MaterialCore(
    val materialId: Int,
    val materialName: String,
    val materialDimension: String,
    val materialSuppliers: List<Supplier>,
)
//enum class StatusOfOrder {
//    New,
//    InProgress,
//    Completed,
//}
