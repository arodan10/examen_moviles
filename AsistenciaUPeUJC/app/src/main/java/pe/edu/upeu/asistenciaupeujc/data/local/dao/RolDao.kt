package pe.edu.upeu.asistenciaupeujc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc.modelo.Rol

@Dao
interface RolDao {
    @Query("SELECT * FROM rol")
    fun getAllRoles(): LiveData<List<Rol>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rol: Rol)

    @Update
    suspend fun update(rol: Rol)

    @Delete
    suspend fun delete(rol: Rol)

    @Query("SELECT * FROM rol WHERE id = :rolId")
    fun getRolById(rolId: Long): LiveData<Rol>
}