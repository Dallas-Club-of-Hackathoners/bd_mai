package stu.mai.bd_mai.database

import androidx.room.Database
import androidx.room.RoomDatabase
import stu.mai.bd_mai.database.dao.CollectionDao
import stu.mai.bd_mai.database.dao.CustomerDao
import stu.mai.bd_mai.database.dao.ExecutorDao
import stu.mai.bd_mai.database.dao.MaterialDao
import stu.mai.bd_mai.database.dao.MaterialsInProductDao
import stu.mai.bd_mai.database.dao.MaterialsSuppliersDao
import stu.mai.bd_mai.database.dao.OrderDao
import stu.mai.bd_mai.database.dao.ProductDao
import stu.mai.bd_mai.database.dao.ProductInOrderDao
import stu.mai.bd_mai.database.dao.SupplierDao
import stu.mai.bd_mai.database.entities.Customer
import stu.mai.bd_mai.database.entities.Executor
import stu.mai.bd_mai.database.entities.Material
import stu.mai.bd_mai.database.entities.MaterialsInProduct
import stu.mai.bd_mai.database.entities.MaterialsSuppliers
import stu.mai.bd_mai.database.entities.Order
import stu.mai.bd_mai.database.entities.Product
import stu.mai.bd_mai.database.entities.ProductInOrder
import stu.mai.bd_mai.database.entities.Supplier

@Database(
    entities = [
        Customer::class,
        Executor::class,
        Material::class,
        Order::class,
        Product::class,
        MaterialsInProduct::class,
        ProductInOrder::class,
        Supplier::class,
        MaterialsSuppliers::class
    ],
    version = 8
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getCustomerDao(): CustomerDao
    abstract fun getExecutorDao(): ExecutorDao
    abstract fun getMaterialDao(): MaterialDao
    abstract fun getOrderDao(): OrderDao
    abstract fun getProductDao(): ProductDao
    abstract fun getMaterialsInProductDao(): MaterialsInProductDao
    abstract fun getProductInOrderDao(): ProductInOrderDao
    abstract fun getSupplierDao(): SupplierDao
    abstract fun getMaterialsSuppliersDao(): MaterialsSuppliersDao
    abstract fun getCollectionDao(): CollectionDao

}

