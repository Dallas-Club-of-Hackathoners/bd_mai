package stu.mai.bd_mai.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.entities.Order

@Dao
interface OrderDao {

        @Query("SELECT * FROM ORDERS")
            fun getAllOrders(): Flow<List<Order>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
           suspend fun insertOrder(order: Order)

        @Delete
           suspend fun deleteOrder(order: Order)
}
