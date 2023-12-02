package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MATERIALS")
data class Material(
    @PrimaryKey val MATERIAL_ID: Int,
    val NAME: String,
    val COUNT: Int
)