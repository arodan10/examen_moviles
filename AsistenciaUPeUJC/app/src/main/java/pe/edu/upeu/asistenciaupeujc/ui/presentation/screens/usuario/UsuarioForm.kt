package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.usuario

import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Usuario
import pe.edu.upeu.asistenciaupeujc.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujc.modelo.Rol
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBox
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.NameTextField
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils

@Composable
fun UsuarioForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: UsuarioFormViewModel = hiltViewModel()
) {
    val usuarioD: Usuario = if (text != "0") {
        Gson().fromJson(text, Usuario::class.java)
    } else {
        val yourRolInstance = Rol(0, "ROLE_ADMIN")
        Usuario(0, "", "", "", "", "", "", "", "", 1, yourRolInstance)
    }

    val isLoading by viewModel.isLoading.observeAsState(false)

    formulario(
        id = usuarioD.id,
        darkMode = darkMode,
        navController = navController,
        usuario = usuarioD,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission", "CoroutineCreationDuringComposition")
@Composable
fun formulario(
    id: Long,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    usuario: Usuario,
    viewModel: UsuarioFormViewModel
) {
    Log.i("VERRR", "d: " + usuario?.id!!)
    val yourRolInstance = Rol(0, "ROLE_ADMIN")
    val person = Usuario(0, "", "", "", "", "", "", "", "", 1, yourRolInstance)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()


    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)) {
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                NameTextField(easyForms = easyForm, text = usuario?.nombres!!, "Nombres:", MyFormKeys.NAME)
                NameTextField(easyForms = easyForm, text = usuario?.apellidos!!, "Apellidos:", MyFormKeys.APELLIDOS)
                NameTextField(easyForms = easyForm, text = usuario?.correo!!, "Correo:", MyFormKeys.CORREO)
                NameTextField(easyForms = easyForm, text = usuario?.password!!, "Password:", MyFormKeys.PASSWORD)
                NameTextField(easyForms = easyForm, text = usuario?.dni!!, "Apellidos:", MyFormKeys.DNI)
                NameTextField(easyForms = easyForm, text = usuario?.perfilPrin!!, "Correo:", MyFormKeys.PRIN)
                var listE = listOf(
                    ComboModel("Activo","Activo"),
                    ComboModel("Desactivo","Desactivo"),
                )
                ComboBox(easyForm = easyForm, "Estado:", usuario?.estado!!, listE)
                var listA = listOf(
                    ComboModel("SI","SI"),
                    ComboModel("NO","NO"),
                )
                ComboBox(easyForm = easyForm, "offlinex:", usuario?.offlinex!!, listA)

                var listR = listOf(
                    ComboModel("1","ROLE_ADMIN"),
                    ComboModel("2","ROLE_USER"),
                )
                ComboBox(easyForm = easyForm, "Rol:", usuario?.rolId.toString(), listR)


                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id) {
                        val lista=easyForm.formData()

                        person.nombres = (lista.get(0) as EasyFormsResult.StringResult).value
                        person.apellidos = (lista.get(1) as EasyFormsResult.StringResult).value
                        person.correo = (lista.get(2) as EasyFormsResult.StringResult).value
                        person.password = (lista.get(3) as EasyFormsResult.StringResult).value
                        person.dni = (lista.get(4) as EasyFormsResult.StringResult).value
                        person.perfilPrin = (lista.get(5) as EasyFormsResult.StringResult).value
                        person.estado = extractCode((lista.get(6) as EasyFormsResult.GenericStateResult<String>).value)
                        person.offlinex = extractCode((lista.get(7) as EasyFormsResult.GenericStateResult<String>).value)
                        val selectedRol = (lista.get(8) as EasyFormsResult.GenericStateResult<String>).value
                        person.rolId = selectedRol.toLong()

                    }

                }
                Spacer()
                AccionButtonCancel(easyForms = easyForm, "Cancelar") {
                    navController.navigate(Destinations.UsuarioUI.route)
                }
            }
        }
    }
}


fun extractCode(data: String): String {
    return if (data.isNotEmpty()) data.split("-")[0] else ""
}
