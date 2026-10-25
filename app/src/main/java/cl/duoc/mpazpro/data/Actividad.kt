package cl.duoc.mpazpro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actividades")
data class Actividad(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val estudianteNombre: String,
    val asignatura: String, // Ej: Lenguaje, Matemáticas
    val puntaje: Int,
    val completada: Boolean = false
)