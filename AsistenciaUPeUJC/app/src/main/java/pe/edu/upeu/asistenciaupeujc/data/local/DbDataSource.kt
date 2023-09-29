package pe.edu.upeu.asistenciaupeujc.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.asistenciaupeujc.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc.data.local.dao.InscritoxDao
import pe.edu.upeu.asistenciaupeujc.data.local.dao.MaterialesxDao
import pe.edu.upeu.asistenciaupeujc.data.local.dao.UsuarioDao
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.Inscritox
import pe.edu.upeu.asistenciaupeujc.modelo.Materialesx
import pe.edu.upeu.asistenciaupeujc.modelo.Usuario

@Database(entities = [Actividad::class, Materialesx::class, Inscritox::class, Usuario::class], version = 3)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao

    abstract fun materialesxDao(): MaterialesxDao

    abstract fun inscritoxDao(): InscritoxDao

    abstract fun usuarioDao(): UsuarioDao
}