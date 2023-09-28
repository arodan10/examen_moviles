package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.usuario


import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import pe.edu.upeu.asistenciaupeujc.modelo.Usuario
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.ConfirmDialog
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import pe.edu.upeu.asistenciaupeujc.R
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.FabItem
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.LoadingCard
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.MultiFloatingActionButton

@Composable
fun UsuarioUI(
    usuarios: List<Usuario>,
    onEditClick: (Usuario) -> Unit,
    onDeleteClick: (Usuario) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(usuarios) { usuario ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Nombres: ${usuario.nombres}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Apellidos: ${usuario.apellidos}",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Correo: ${usuario.correo}",
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = { onEditClick(usuario) }
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                        IconButton(
                            onClick = { onDeleteClick(usuario) }
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}
