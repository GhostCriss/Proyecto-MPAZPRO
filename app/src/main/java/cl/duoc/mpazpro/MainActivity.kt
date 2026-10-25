package cl.duoc.mpazpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import cl.duoc.mpazpro.data.AppDatabase
import cl.duoc.mpazpro.data.UsuarioRepository
import cl.duoc.mpazpro.navigation.AppNavigation // Importación clave de tu sistema de rutas
import cl.duoc.mpazpro.ui.theme.MPAZPROTheme
import cl.duoc.mpazpro.viewmodel.UsuarioViewModel
import cl.duoc.mpazpro.viewmodel.UsuarioViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Instanciar la base de datos y el repositorio
        val database = AppDatabase.getDatabase(this)
        val repository = UsuarioRepository(database.usuarioDao())

        // 2. Crear el ViewModel usando el Factory
        val factory = UsuarioViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[UsuarioViewModel::class.java]

        setContent {
            MPAZPROTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // AQUÍ ESTÁ LA MAGIA: Llamamos al sistema de navegación pasándole la base de datos
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}