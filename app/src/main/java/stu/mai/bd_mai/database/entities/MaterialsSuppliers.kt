package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "MATERIALS_SUPPLIERS",
    primaryKeys = ["SUPPLIER_ID", "MATERIAL_ID"],
    foreignKeys = [
        ForeignKey(
            entity = Supplier::class,
            parentColumns = ["SUPPLIER_ID"],
            childColumns = ["SUPPLIER_ID"]
        ),
        ForeignKey(
            entity = Material::class,
            parentColumns = ["MATERIAL_ID"],
            childColumns = ["MATERIAL_ID"]
        )
    ]
)
data class MaterialsSuppliers(
    val SUPPLIER_ID: Int,
    val MATERIAL_ID: Int,
    val COUNT: Int
)