package cl.duoc.mpazpro.data

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    // --- Usuarios ---
    val todosLosUsuarios = usuarioDao.obtenerTodosLosUsuarios()

    suspend fun agregarUsuario(usuario: Usuario) {
        usuarioDao.insertarUsuario(usuario)
    }

    // --- Actividades ---
    val todasLasActividades = usuarioDao.obtenerTodasLasActividades()

    suspend fun agregarActividad(actividad: Actividad) {
        usuarioDao.insertarActividad(actividad)
    }
}