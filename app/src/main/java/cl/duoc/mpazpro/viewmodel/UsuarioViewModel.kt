package cl.duoc.mpazpro.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cl.duoc.mpazpro.data.Actividad
import cl.duoc.mpazpro.data.Usuario
import cl.duoc.mpazpro.data.UsuarioRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    // Variable para recordar quién inició sesión
    var nombreEstudianteActual by mutableStateOf("")

    // Expone la lista de usuarios a la interfaz visual (View) de forma reactiva
    val usuarios: StateFlow<List<Usuario>> = repository.todosLosUsuarios
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val actividades: StateFlow<List<Actividad>> = repository.todasLasActividades
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Función que la interfaz llamará al apretar un botón
    fun guardarUsuario(nombre: String, rol: String) {
        viewModelScope.launch {
            repository.agregarUsuario(Usuario(nombre = nombre, rol = rol))
        }
    }

    fun guardarActividad(estudianteNombre: String, asignatura: String, puntaje: Int, completada: Boolean) {
        viewModelScope.launch {
            repository.agregarActividad(
                Actividad(
                    estudianteNombre = estudianteNombre,
                    asignatura = asignatura,
                    puntaje = puntaje,
                    completada = completada
                )
            )
        }
    }
}

// Necesario para construir el ViewModel pasándole el Repository
class UsuarioViewModelFactory(private val repository: UsuarioRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsuarioViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}