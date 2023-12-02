package stu.mai.bd_mai.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import stu.mai.bd_mai.database.entities.Customer
import stu.mai.bd_mai.database.entities.Executor
import stu.mai.bd_mai.database.entities.Material
import stu.mai.bd_mai.database.entities.MaterialsInProduct
import stu.mai.bd_mai.database.entities.MaterialsSuppliers
import stu.mai.bd_mai.database.entities.Order
import stu.mai.bd_mai.database.entities.Product
import stu.mai.bd_mai.database.entities.ProductInOrder
import stu.mai.bd_mai.database.entities.Supplier

@Dao
interface CommonDao {
    // Customer operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCustomer(customer: Customer)

    @Query("SELECT * FROM CUSTOMERS")
    suspend fun getAllCustomers(): List<Customer>

    // Executor operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExecutor(executor: Executor)

    @Query("SELECT * FROM EXECUTORS")
    suspend fun getAllExecutors(): List<Executor>

    // Material operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMaterial(material: Material)

    @Query("SELECT * FROM MATERIALS")
    suspend fun getAllMaterials(): List<Material>

    // Order operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM ORDERS")
    suspend fun getAllOrders(): List<Order>

    // Product operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM PRODUCTS")
    suspend fun getAllProducts(): List<Product>

    // MaterialsInProduct operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMaterialsInProduct(materialsInProduct: MaterialsInProduct)

    @Query("SELECT * FROM MATERIALS_IN_PRODUCT")
    suspend fun getAllMaterialsInProducts(): List<MaterialsInProduct>

    // ProductInOrder operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProductInOrder(productInOrder: ProductInOrder)

    @Query("SELECT * FROM PRODUCT_IN_ORDERS")
    suspend fun getAllProductsInOrders(): List<ProductInOrder>

    // Supplier operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSupplier(supplier: Supplier)

    @Query("SELECT * FROM SUPPLIERS")
    suspend fun getAllSuppliers(): List<Supplier>

    // MaterialsSuppliers operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMaterialsSuppliers(materialsSuppliers: MaterialsSuppliers)

    @Query("SELECT * FROM MATERIALS_SUPPLIERS")
    suspend fun getAllMaterialsSuppliers(): List<MaterialsSuppliers>

    // Add other specific queries as needed

    // You can also include more complex queries that involve multiple tables
}
