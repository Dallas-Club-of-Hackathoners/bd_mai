package stu.mai.bd_mai.features.orders.repositories

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Material
import stu.mai.bd_mai.database.entities.Order
import stu.mai.bd_mai.database.entities.Product
import stu.mai.bd_mai.database.entities.Supplier
import stu.mai.bd_mai.features.orders.domain.entity.MaterialCore
import stu.mai.bd_mai.features.orders.domain.entity.OrderCore
import stu.mai.bd_mai.features.orders.domain.entity.ProductCore
import javax.inject.Inject

class CheckRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : CheckRepository {

    override suspend fun getOrderByOrderId(orderId: Int): OrderCore {
        val order = database.getOrderDao().getOrderById(orderId)
        return getOrderCore(order)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getOrderList(): Flow<List<OrderCore>> {
        return database.getOrderDao().getAllOrders().flatMapConcat { orderList ->
            flow {
                emit(orderList.map {
                    getOrderCore(it)
                })
            }
        }

    }

    override suspend fun deleteOrder(orderId: Int) {
        database.getOrderDao().deleteOrderById(orderId)
    }

    private suspend fun getOrderCore(order:Order) : OrderCore {
        val customer = database.getCustomerDao().getCustomerById(order.CUSTOMER_ID)
        val executor = database.getExecutorDao().getExecutorById(order.EXECUTOR_ID)
        val products = getProductsInOrder(order.ORDER_ID)
        val productsCore = products.map {
            getProductCore(it)
        }

        val orderPrice = productsCore.sumOf {
            it.productPrice
        }
        return OrderCore(
            orderId = order.ORDER_ID,
            orderData = order.ORDER_DATE,
            orderStatus = order.STATUS_OF_ORDER,
            orderExecutor = executor,
            orderCustomer = customer,
            orderPrice = orderPrice,
            orderProduct = productsCore
        )

    }

    private suspend fun getProductCore(product: Product) : ProductCore {
        val materials = getMaterialsInOrder(product)
        val materialsCore = materials.map {
            getMaterialCore(it)
        }

        return ProductCore(
            productId = product.PRODUCT_ID,
            productName = product.NAME,
            productPrice = product.PRICE,
            productDescription = product.DESCRIPTION,
            productMaterials = materialsCore
        )
    }

    private suspend fun getMaterialCore(material: Material) : MaterialCore {
        val suppliers = getSuppliersByMaterial(material)
        return MaterialCore(
            materialId = material.MATERIAL_ID,
            materialName = material.NAME,
            materialDimension = material.DIMENSION,
            materialSuppliers = suppliers
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun getProductsInOrder(orderId: Int): List<Product> {
        return database.getProductInOrderDao().getProductsInOrderById(orderId).flatMapConcat {
            productInOrderList ->
            flow {
                for ( productInOrder in productInOrderList) {
                    emit(database.getProductDao().getProductById(productInOrder.PRODUCT_ID))
                }
            }
        }.toList()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun getMaterialsInOrder(product: Product): List<Material> {
        return database.getMaterialsInProductDao()
            .getMaterialsInProductByProductId(product.PRODUCT_ID)
            .flatMapConcat { materialsInProductList ->
                flow {
                    for (materialsInProduct in materialsInProductList) {
                        emit(
                            database.getMaterialDao().getMaterialById(materialsInProduct.MATERIAL_ID)
                        )
                    }
                }
            }.toList()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun getSuppliersByMaterial(materials: Material): List<Supplier> {
        return database.getMaterialsSuppliersDao()
            .getMaterialsSuppliersByMaterialId(materials.MATERIAL_ID)
            .flatMapConcat { suppliersInMaterialList ->
                flow {
                    for (supplierInMaterial in suppliersInMaterialList) {
                        emit(
                            database.getSupplierDao().getSupplierById(supplierInMaterial.SUPPLIER_ID)
                        )
                    }
                }
            }.toList()
    }



}