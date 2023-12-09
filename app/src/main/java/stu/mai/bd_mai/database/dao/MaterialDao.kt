package stu.mai.bd_mai.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.entities.Material

@Dao
interface MaterialDao {

        @Query("SELECT * FROM MATERIALS")
            fun getAllMaterials(): Flow<List<Material>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
            suspend fun insertMaterial(material: Material)

        @Delete
            suspend fun deleteMaterial(material: Material)

}
