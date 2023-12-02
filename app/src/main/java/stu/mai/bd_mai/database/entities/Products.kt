package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "PRODUCTS",
    foreignKeys = [
        ForeignKey(
            entity = Material::class,
            parentColumns = ["MATERIAL_ID"],
            childColumns = ["MATERIAL_ID"]
        )
    ]
)
data class Product(
    @PrimaryKey val PRODUCT_ID: Int,
    val NAME: String,
    val PRICE: Double,
    val DESCRIPTION: String,
    val MATERIAL_ID: Int
)