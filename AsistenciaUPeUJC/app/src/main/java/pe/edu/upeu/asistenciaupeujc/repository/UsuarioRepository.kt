package pe.edu.upeu.asistenciaupeujc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.data.local.dao.UsuarioxDao
import pe.edu.upeu.asistenciaupeujc.data.remote.RestUsuario
import pe.edu.upeu.asistenciaupeujc.modelo.Usuario
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils
import javax.inject.Inject
import pe.edu.upeu.asistenciaupeujc.modelo.UsuarioDto
import pe.edu.upeu.asistenciaupeujc.modelo.UsuarioResp
import retrofit2.Response

interface UsuarioRepository {
    suspend fun loginUsuario(user: UsuarioDto): Response<UsuarioResp>
    suspend fun eliminarUsuario(usuario: Usuario)
    fun obtenerTodosUsuarios(): LiveData<List<Usuario>>
    fun buscarUsuarioPorId(id: Long): LiveData<Usuario>
    suspend fun insertarUsuario(usuario: Usuario): Boolean
    suspend fun modificarUsuario(usuario: Usuario): Boolean
}

class UsuarioRepositoryImp @Inject constructor(private val restUsuario: RestUsuario,
                                               private val usuarioDao: UsuarioxDao) : UsuarioRepository {
    override suspend fun loginUsuario(user: UsuarioDto): Response<UsuarioResp> {
        return restUsuario.login(user)
    }

    override suspend fun eliminarUsuario(usuario: Usuario) {
        CoroutineScope(Dispatchers.IO).launch {
            restUsuario.eliminarUsuario(TokenUtils.TOKEN_CONTENT, usuario.id)
        }
        usuarioDao.eliminarUsuario(usuario.id)
    }

    override fun obtenerTodosUsuarios(): LiveData<List<Usuario>> {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = restUsuario.obtenerTodosUsuarios(TokenUtils.TOKEN_CONTENT)
                if (response.isSuccessful) {
                    val data = response.body()!!
                    usuarioDao.insertarUsuarios(data)
                } else {
                    Log.i("ERROR", "Error en la respuesta de la API: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.i("ERROR", "Error: ${e.message}")
            }
        }
        return usuarioDao.obtenerTodosUsuarios()
    }



    override fun buscarUsuarioPorId(id: Long): LiveData<Usuario> {
        return usuarioDao.buscarUsuarioPorId(id)
    }

    override suspend fun insertarUsuario(usuario: Usuario): Boolean {
        return restUsuario.insertarUsuario(TokenUtils.TOKEN_CONTENT, usuario).body() != null
    }

    override suspend fun modificarUsuario(usuario: Usuario): Boolean {
        return restUsuario.modificarUsuario(TokenUtils.TOKEN_CONTENT, usuario.id, usuario).body() != null
    }
}