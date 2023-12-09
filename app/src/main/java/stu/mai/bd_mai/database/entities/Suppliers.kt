package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SUPPLIERS")
data class Supplier(
    @PrimaryKey(autoGenerate = true)
    val SUPPLIER_ID: Int = 0,
    val NAME: String,
    val PHONE: String?,
    val EMAIL: String?
)