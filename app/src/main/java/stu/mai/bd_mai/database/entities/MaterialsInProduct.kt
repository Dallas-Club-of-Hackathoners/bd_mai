package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "MATERIALS_IN_PRODUCT",
    primaryKeys = ["MATERIAL_ID", "PRODUCT_ID"],
    foreignKeys = [
        ForeignKey(
            entity = Material::class,
            parentColumns = ["MATERIAL_ID"],
            childColumns = ["MATERIAL_ID"]
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["PRODUCT_ID"],
            childColumns = ["PRODUCT_ID"]
        )
    ]
)
data class MaterialsInProduct(
    val MATERIAL_ID: Int,
    val PRODUCT_ID: Int,
    val COUNT: Int
)