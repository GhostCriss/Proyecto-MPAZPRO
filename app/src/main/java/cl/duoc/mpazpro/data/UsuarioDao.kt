package cl.duoc.mpazpro.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    // Inserta un usuario nuevo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarUsuario(usuario: Usuario): Long

    // Obtiene una lista actualizada en tiempo real (Flow)
    @Query("SELECT * FROM usuarios")
    fun obtenerTodosLosUsuarios(): Flow<List<Usuario>>

    // --- Consultas para Actividades ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarActividad(actividad: Actividad): Long

    @Query("SELECT * FROM actividades")
    fun obtenerTodasLasActividades(): Flow<List<Actividad>>

}