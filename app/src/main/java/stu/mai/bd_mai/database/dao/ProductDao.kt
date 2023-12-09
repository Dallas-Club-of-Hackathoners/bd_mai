package stu.mai.bd_mai.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.entities.Product

@Dao
interface ProductDao {

        @Query("SELECT * FROM PRODUCTS")
            fun getAllProducts(): Flow<List<Product>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
          suspend fun insertProduct(product: Product)

        @Delete
          suspend fun deleteProduct(product: Product)
}
