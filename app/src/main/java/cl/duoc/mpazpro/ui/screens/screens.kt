package cl.duoc.mpazpro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "MPAZ PRO", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("home_estudiante") }) {
            Text("Ingresar como Estudiante")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("home_docente") }) {
            Text("Ingresar como Docente")
        }
    }
}

@Composable
fun HomeEstudianteScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Panel del Estudiante")
        Button(onClick = { navController.popBackStack() }) {
            Text("Cerrar Sesión")
        }
    }
}

@Composable
fun HomeDocenteScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Panel del Docente")
        Button(onClick = { navController.popBackStack() }) {
            Text("Cerrar Sesión")
        }
    }
}