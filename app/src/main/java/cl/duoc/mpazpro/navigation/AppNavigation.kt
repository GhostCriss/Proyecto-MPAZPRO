

package cl.duoc.mpazpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duoc.mpazpro.ui.screens.* // Importarás tus pantallas

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // El startDestination define qué pantalla se abre primero al iniciar la app
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("home_docente") {
            HomeDocenteScreen(navController = navController)
        }

        composable("home_estudiante") {
            HomeEstudianteScreen(navController = navController)
        }
    }
}