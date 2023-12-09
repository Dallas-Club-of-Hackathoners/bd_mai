package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "PRODUCT_IN_ORDER",
    primaryKeys = ["ORDER_ID", "PRODUCT_ID"],
    foreignKeys = [
        ForeignKey(
            entity = Order::class,
            parentColumns = ["ORDER_ID"],
            childColumns = ["ORDER_ID"]
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["PRODUCT_ID"],
            childColumns = ["PRODUCT_ID"]
        )
    ]
)
data class ProductInOrder(
    val ORDER_ID: Int,
    val PRODUCT_ID: Int,
    val COUNT: Int
)