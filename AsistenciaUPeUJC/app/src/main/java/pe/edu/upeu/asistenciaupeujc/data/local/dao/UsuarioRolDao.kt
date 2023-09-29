package pe.edu.upeu.asistenciaupeujc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc.modelo.UsuarioRol

@Dao
interface UsuarioRolDao {
    @Query("SELECT * FROM usuario_rol")
    fun getAllUsuarioRoles(): LiveData<List<UsuarioRol>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuarioRol: UsuarioRol)

    @Update
    suspend fun update(usuarioRol: UsuarioRol)

    @Delete
    suspend fun delete(usuarioRol: UsuarioRol)

}