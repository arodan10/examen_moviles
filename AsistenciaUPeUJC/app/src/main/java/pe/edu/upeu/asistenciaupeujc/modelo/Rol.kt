package pe.edu.upeu.asistenciaupeujc.modelo

import androidx.room.Entity


@Entity(tableName = "rol")
data class Rol(
    val id: Long,
    val rolNombre: RolNombre,
    val usuarios: List<Usuario> = emptyList()  // Relación de uno a muchos con usuarios
)

enum class RolNombre {
    ROLE_ADMIN,
    ROLE_USER
}