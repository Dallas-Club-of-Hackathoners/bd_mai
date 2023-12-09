package stu.mai.bd_mai.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.entities.Executor
@Dao
interface ExecutorDao {

    @Query("SELECT * FROM EXECUTORS")
        fun getAllExecutors(): Flow<List<Executor>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertExecutor(executor: Executor)

    @Delete
        suspend fun deleteExecutor(executor: Executor)

    @Query("SELECT * FROM EXECUTORS WHERE EXECUTOR_ID = :executorId")
    suspend fun getExecutorById(executorId: Int): Executor
}
