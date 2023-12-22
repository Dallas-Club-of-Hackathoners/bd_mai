package stu.mai.bd_mai.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.features.collections.domain.entity.MaterialWithTotalUsed
import stu.mai.bd_mai.features.collections.domain.entity.SupplierWithTotalMaterials

@Dao
interface CollectionDao {

    @Query("""
        SELECT MATERIALS.*, SUM(MATERIALS_IN_PRODUCT.COUNT) AS TOTAL_USED
        FROM MATERIALS
        JOIN MATERIALS_IN_PRODUCT ON MATERIALS.MATERIAL_ID = MATERIALS_IN_PRODUCT.MATERIAL_ID
        GROUP BY MATERIALS.MATERIAL_ID
        ORDER BY TOTAL_USED DESC
    """)
     fun getMaterialsWithTotalUsed(): Flow<List<MaterialWithTotalUsed>>

    @Query("""
        SELECT SUPPLIERS.*, COUNT(MATERIALS_SUPPLIERS.MATERIAL_ID) AS TOTAL_MATERIALS_PROVIDED
        FROM SUPPLIERS
        JOIN MATERIALS_SUPPLIERS ON SUPPLIERS.SUPPLIER_ID = MATERIALS_SUPPLIERS.SUPPLIER_ID
        GROUP BY SUPPLIERS.SUPPLIER_ID
        ORDER BY TOTAL_MATERIALS_PROVIDED DESC
    """)
    fun getSuppliersWithTotalMaterials(): Flow<List<SupplierWithTotalMaterials>>




}