package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MATERIALS")
data class Material(
    @PrimaryKey(autoGenerate = true)
    val MATERIAL_ID: Int = 0,
    val NAME: String,
    val DIMENSION: String
)