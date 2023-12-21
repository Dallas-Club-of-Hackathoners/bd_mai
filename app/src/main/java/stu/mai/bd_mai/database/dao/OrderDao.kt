package stu.mai.bd_mai.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.entities.Order

@Dao
interface OrderDao {

    @Query("SELECT MAX(ORDER_ID) FROM ORDERS")
    suspend fun getMaxOrderId(): Int

    @Query("SELECT * FROM ORDERS")
            fun getAllOrders(): List<Order>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
           suspend fun insertOrder(order: Order)

        @Delete
           suspend fun deleteOrder(order: Order)

        @Query("DELETE FROM ORDERS WHERE ORDER_ID = :orderId")
            suspend fun deleteOrderById(orderId: Int)

        // Получить заказ по его идентификатору
        @Query("SELECT * FROM ORDERS WHERE ORDER_ID = :orderId")
            suspend fun getOrderById(orderId: Int): Order

        // Обновить заказ
         @Update
            suspend fun updateOrder(order: Order)
}
