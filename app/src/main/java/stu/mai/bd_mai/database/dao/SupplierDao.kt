package stu.mai.bd_mai.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.entities.Supplier

@Dao
interface SupplierDao {

    @Query("SELECT * FROM SUPPLIERS WHERE SUPPLIER_ID = :supplierId")
    suspend fun getSupplierById(supplierId: Int): Supplier

    @Query("SELECT * FROM SUPPLIERS")
    fun getAllSuppliers(): Flow<List<Supplier>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSupplier(supplier: Supplier)

    @Delete
    suspend fun deleteSupplier(supplier: Supplier)
}