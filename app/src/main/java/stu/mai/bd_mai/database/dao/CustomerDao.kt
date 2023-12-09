package stu.mai.bd_mai.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.entities.Customer
@Dao
interface CustomerDao {

    @Query("SELECT * FROM CUSTOMERS")
        fun getAllCustomers(): Flow<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertCustomer(customer: Customer)

    @Delete
        suspend fun deleteCustomer(customer: Customer)

}
