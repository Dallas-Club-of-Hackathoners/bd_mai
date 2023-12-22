package stu.mai.bd_mai.features.collections.domain.entity

import androidx.room.ColumnInfo

data class MaterialWithTotalUsed(
    @ColumnInfo(name = "MATERIAL_ID") val materialId: Int,
    @ColumnInfo(name = "NAME") val material: String,
    @ColumnInfo(name = "DIMENSION") val dimension: String,
    @ColumnInfo(name = "TOTAL_USED") val totalUsed: Int
)

data class SupplierWithTotalMaterials(
    @ColumnInfo(name = "SUPPLIER_ID") val supplierId: Int,
    @ColumnInfo(name = "SUPPLIER_NAME") val supplierName: String,
    @ColumnInfo(name = "TOTAL_MATERIALS_PROVIDED") val totalMaterialsProvided: Int
)
