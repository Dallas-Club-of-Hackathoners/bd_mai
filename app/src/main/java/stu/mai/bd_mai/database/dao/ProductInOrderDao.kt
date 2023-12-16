package stu.mai.bd_mai.database.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.entities.ProductInOrder

@Dao
interface ProductInOrderDao {

        @Query("SELECT * FROM PRODUCT_IN_ORDER WHERE ORDER_ID = :orderId")
            fun getProductsInOrderById(orderId: Int): Flow<List<ProductInOrder>>

        @Query("SELECT * FROM PRODUCT_IN_ORDER")
            fun getProductsInOrderById(): Flow<List<ProductInOrder>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
           suspend fun insertProductInOrder(productInOrder: ProductInOrder)

        @Delete
           suspend fun deleteProductInOrder(productInOrder: ProductInOrder)
}
