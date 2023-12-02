package stu.mai.bd_mai.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EXECUTORS")
data class Executor(
    @PrimaryKey val EXECUTOR_ID: Int,
    val NAME: String,
    val PHONE: String?,
    val EMAIL: String?
)