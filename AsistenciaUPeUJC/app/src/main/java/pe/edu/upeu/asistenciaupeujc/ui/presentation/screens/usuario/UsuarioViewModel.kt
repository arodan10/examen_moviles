package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.usuario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.MaterialesxConActividad
import pe.edu.upeu.asistenciaupeujc.modelo.Rol
import pe.edu.upeu.asistenciaupeujc.repository.MaterialesxRepository
import pe.edu.upeu.asistenciaupeujc.repository.UsuarioRepository
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val usuarioRepo: UsuarioRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val activ: LiveData<List<Rol>> by lazy {
        usuarioRepo.UsuarioRepository()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addUsuarios() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun eliminarUsuario(toDelete: Rol) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            usuarioRepo.eliminarUsuario(toDelete);
        }
    }

}