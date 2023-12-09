package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CUSTOMERS")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val CUSTOMER_ID: Int = 0,
    val NAME: String,
    val PHONE: String?,
    val EMAIL: String?
)
