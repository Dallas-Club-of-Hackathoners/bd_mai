package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "PRODUCTS",

)
data class Product(
    @PrimaryKey(autoGenerate = true)
    val PRODUCT_ID: Int = 0,
    val NAME: String,
    val PRICE: Double,
    val DESCRIPTION: String,
)