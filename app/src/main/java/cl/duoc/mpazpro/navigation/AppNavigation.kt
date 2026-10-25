package cl.duoc.mpazpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duoc.mpazpro.ui.screens.*
import cl.duoc.mpazpro.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation(viewModel: UsuarioViewModel) {
    val navController = rememberNavController()

    // startDestination = "inicio" define que la app siempre arranca en el menú de selección
    NavHost(navController = navController, startDestination = "inicio") {

        composable(route = "inicio") {
            InicioScreen(navController = navController)
        }

        composable(route = "login_docente") {
            LoginDocenteScreen(navController = navController)
        }

        composable(route = "home_estudiante") {
            HomeEstudianteScreen(navController = navController)
        }

        composable(route = "home_docente") {
            // Solo el docente tiene acceso al ViewModel para ver y guardar datos
            HomeDocenteScreen(navController = navController, viewModel = viewModel)
        }

        composable(route = "juego") {
            JuegoScreen(navController = navController, viewModel = viewModel)
        }

        composable(route = "login_estudiante") {
            LoginEstudianteScreen(navController = navController, viewModel = viewModel)
        }

        composable(route = "progreso_estudiante") {
            // Le pasamos el ViewModel para que sepa quién es el alumno
            ProgresoEstudianteScreen(navController = navController, viewModel = viewModel)
        }
    }
}