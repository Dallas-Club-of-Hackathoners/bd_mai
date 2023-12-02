package stu.mai.bd_mai.database

import androidx.room.Database
import androidx.room.RoomDatabase
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
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun commonDao(): CommonDao
}

