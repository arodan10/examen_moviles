package pe.edu.upeu.asistenciaupeujc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc.modelo.Usuario

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarUsuario(usuario: Usuario)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarUsuarios(usuario: List<Usuario>)

    @Update
    suspend fun modificarUsuario(usuario: Usuario)

    @Query("DELETE FROM usuario WHERE id = :id")
    suspend fun eliminarUsuario(id: Long)

    @Query("SELECT * FROM usuario")
    fun obtenerTodosUsuarios(): LiveData<List<Usuario>>

    @Query("SELECT * FROM usuario WHERE id = :id")
    fun buscarUsuarioPorId(id: Long): LiveData<Usuario>
}
