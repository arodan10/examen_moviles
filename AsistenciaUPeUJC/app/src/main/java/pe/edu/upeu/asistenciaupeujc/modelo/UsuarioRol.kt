package pe.edu.upeu.asistenciaupeujc.modelo

import androidx.room.Entity

@Entity(tableName = "usuario_rol")
data class UsuarioRol(
    val usuarioId: Long,
    val rolId: Long
)
