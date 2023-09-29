package pe.edu.upeu.asistenciaupeujc.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
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
    var offlinex: String
)

data class UsuarioReport(
    var id: Long,
    var nombres: String,
    var apellidos: String,
    var correo: String,
    var password: String,
    var dni: String,
    var perfilPrin: String,
    var estado: String,
    var offlinex: String
)