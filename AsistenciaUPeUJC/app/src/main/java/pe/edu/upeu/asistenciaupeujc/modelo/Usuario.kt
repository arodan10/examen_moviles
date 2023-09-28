package pe.edu.upeu.asistenciaupeujc.modelo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "usuario",
    foreignKeys = [
        ForeignKey(
            entity = Rol::class,
            parentColumns = ["id"],
            childColumns = ["rolId"],
            onDelete = ForeignKey.CASCADE // or other actions like ForeignKey.NO_ACTION
        )
    ]
)
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var nombres: String,
    var apellidos: String,
    var correo: String,
    var password: String,
    var dni: String,
    var perfilPrin: String,
    var estado: String,
    var offlinex: String,
    var rolId: Long,
    var roles: Set<String> = emptySet(),
    var rol: Rol
)

@Entity(tableName = "gobal_rol")
data class Rol(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val rolNombre: String
)

