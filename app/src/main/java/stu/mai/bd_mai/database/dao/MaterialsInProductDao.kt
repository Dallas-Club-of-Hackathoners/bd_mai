package stu.mai.bd_mai.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.entities.MaterialsInProduct

@Dao
interface MaterialsInProductDao {

        @Query("SELECT * FROM MATERIALS_IN_PRODUCT WHERE MATERIAL_ID = :materialId")
            fun getMaterialsInProductByMaterialId(materialId: Int): Flow<List<MaterialsInProduct>>

        @Query("SELECT * FROM MATERIALS_IN_PRODUCT WHERE PRODUCT_ID = :productId")
            fun getMaterialsInProductByProductId(productId: Int): List<MaterialsInProduct>

        @Query("SELECT * FROM MATERIALS_IN_PRODUCT")
            fun getAllMaterialsInProducts(): Flow<List<MaterialsInProduct>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
            suspend fun insertMaterialsInProduct(materialsInProduct: MaterialsInProduct)

        @Delete
            suspend fun deleteMaterialsInProduct(materialsInProduct: MaterialsInProduct)
}
