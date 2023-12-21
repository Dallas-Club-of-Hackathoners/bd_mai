package stu.mai.bd_mai.features.orders.repositories

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Customer
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
        val tmp = order
        val result = getOrderCore(order)
        return result
    }

    override suspend fun getOrderList(): List<OrderCore> {
        val orderList = database.getOrderDao().getAllOrders()

        return orderList.map {
            getOrderCore(it)
        }
    }

    override suspend fun deleteOrder(orderId: Int) {
        database.getOrderDao().deleteOrderById(orderId)
    }

    private suspend fun getOrderCore(order:Order) : OrderCore {
        val customer = database.getCustomerDao().getCustomerById(order.CUSTOMER_ID)
        val executor = database.getExecutorDao().getExecutorById(order.EXECUTOR_ID)
        val products = getProductsInOrder(order.ORDER_ID)
        val count = database.getProductInOrderDao().getProductsInOrderById(orderId = order.ORDER_ID).first().COUNT
        val productsCore = products.map {
            getProductCore(it)
        }

        var orderPrice = 0.0

        for (product in productsCore) {
             orderPrice+= product.productPrice * count
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

    private suspend fun getProductsInOrder(orderId: Int): List<Product> {
        val productInOrderList = database.getProductInOrderDao().getProductsInOrderById(orderId)

        return productInOrderList.map { productInOrder ->
            database.getProductDao().getProductById(productInOrder.PRODUCT_ID)
        }
    }


    private suspend fun getMaterialsInOrder(product: Product): List<Material> {
        val materialsInProductList =
            database.getMaterialsInProductDao().getMaterialsInProductByProductId(product.PRODUCT_ID)

        return materialsInProductList.map { materialsInProduct ->
            database.getMaterialDao().getMaterialById(materialsInProduct.MATERIAL_ID)
        }
    }



    private suspend fun getSuppliersByMaterial(material: Material): List<Supplier> {
        val suppliersInMaterialList =
            database.getMaterialsSuppliersDao().getMaterialsSuppliersByMaterialId(material.MATERIAL_ID)

        return suppliersInMaterialList.map { supplierInMaterial ->
            database.getSupplierDao().getSupplierById(supplierInMaterial.SUPPLIER_ID)
        }
    }



}