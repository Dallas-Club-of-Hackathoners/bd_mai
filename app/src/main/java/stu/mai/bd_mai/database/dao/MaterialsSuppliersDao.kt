package stu.mai.bd_mai.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.entities.MaterialsSuppliers

@Dao
interface MaterialsSuppliersDao {

    @Query("SELECT * FROM MATERIALS_SUPPLIERS WHERE SUPPLIER_ID = :supplierId")
    fun getMaterialsSuppliersBySupplierId(supplierId: Int): Flow<List<MaterialsSuppliers>>

    @Query("SELECT * FROM MATERIALS_SUPPLIERS WHERE MATERIAL_ID = :materialId")
    fun getMaterialsSuppliersByMaterialId(materialId: Int): List<MaterialsSuppliers>

    @Query("SELECT * FROM MATERIALS_SUPPLIERS")
    fun getAllMaterialsSuppliers(): Flow<List<MaterialsSuppliers>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMaterialsSuppliers(materialsSuppliers: MaterialsSuppliers)

    @Delete
    suspend fun deleteMaterialsSuppliers(materialsSuppliers: MaterialsSuppliers)
}
