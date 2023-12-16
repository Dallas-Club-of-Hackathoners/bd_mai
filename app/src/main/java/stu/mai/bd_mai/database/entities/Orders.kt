package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "ORDERS",
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["CUSTOMER_ID"],
            childColumns = ["CUSTOMER_ID"]
        ),
        ForeignKey(
            entity = Executor::class,
            parentColumns = ["EXECUTOR_ID"],
            childColumns = ["EXECUTOR_ID"]
        ),

    ]
)
data class Order(
    @PrimaryKey(autoGenerate = true)
    val ORDER_ID: Int = 0,
    val CUSTOMER_ID: Int,
    val EXECUTOR_ID: Int,
    val ORDER_DATE: String,
    val STATUS_OF_ORDER: String,
)
