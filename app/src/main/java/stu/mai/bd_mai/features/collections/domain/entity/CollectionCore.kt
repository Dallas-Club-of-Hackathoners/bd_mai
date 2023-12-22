package stu.mai.bd_mai.features.collections.domain.entity

import android.content.ClipDescription
import androidx.room.ColumnInfo

data class MaterialWithTotalUsed(
    @ColumnInfo(name = "MATERIAL_ID") val materialId: Int,
    @ColumnInfo(name = "NAME") val material: String,
    @ColumnInfo(name = "DIMENSION") val dimension: String,
    @ColumnInfo(name = "TOTAL_USED") val totalUsed: Int
)

data class SupplierWithTotalMaterials(
    @ColumnInfo(name = "SUPPLIER_ID") val supplierId: Int,
    @ColumnInfo(name = "NAME") val supplierName: String,
    @ColumnInfo(name = "PHONE") val supplierPhone: String,
    @ColumnInfo(name = "EMAIL") val supplierEmail: String,
    @ColumnInfo(name = "TOTAL_MATERIALS_PROVIDED") val totalMaterialsProvided: Int
)

data class CustomerWithTotalPurchase(
    @ColumnInfo(name = "CUSTOMER_ID") val customerId: Int,
    @ColumnInfo(name = "NAME")  val customerName: String,
    @ColumnInfo(name = "PHONE") val customerPhone: String,
    @ColumnInfo(name = "EMAIL") val customerEmail: String,
    @ColumnInfo(name = "TOTAL_PURCHASE_AMOUNT") val totalPurchaseAmount: Double
)

data class ProductWithTotalSold(
    @ColumnInfo(name = "PRODUCT_ID") val productId: Int,
    @ColumnInfo(name = "NAME") val productName: String,
    @ColumnInfo(name = "PRICE") val productPrice:Double,
    @ColumnInfo(name = "DESCRIPTION") val productDescription: String,
    @ColumnInfo(name = "TOTAL_SOLD") val totalSold: Int
)




