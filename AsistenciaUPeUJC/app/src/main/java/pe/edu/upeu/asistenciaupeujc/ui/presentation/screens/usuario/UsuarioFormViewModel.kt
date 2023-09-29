package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.usuario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.Usuario
import pe.edu.upeu.asistenciaupeujc.repository.ActividadRepository
import pe.edu.upeu.asistenciaupeujc.repository.UsuarioRepository
import javax.inject.Inject

@HiltViewModel
class UsuarioFormViewModel @Inject constructor(
    private val userRepo: UsuarioRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getUsuario(idX: Long): LiveData<Usuario> {
        return userRepo.buscarUsuarioId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addActividad(usuario: Usuario){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", usuario.toString())
            userRepo.insertarUsuario(usuario)
        }
    }
    fun editActividad(usuario: Usuario){
        viewModelScope.launch(Dispatchers.IO){
            userRepo.modificarRemoteUsuario(usuario)
        }
    }
}