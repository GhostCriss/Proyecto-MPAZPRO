package cl.duoc.mpazpro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import cl.duoc.mpazpro.viewmodel.UsuarioViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.ui.text.style.TextAlign
import java.io.File
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person

// 1. Pantalla Inicial
@Composable
fun InicioScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color(0xFFE3F2FD), Color.White))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MPAZ PRO", 
            fontSize = 48.sp, 
            fontWeight = FontWeight.Bold, 
            color = Color(0xFF1565C0)
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("login_estudiante") },
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
        ) { // ACTUALIZADO
            Icon(Icons.Default.Face, contentDescription = "Estudiante")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ingresar como Estudiante", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("login_docente") },
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Icon(Icons.Default.Person, contentDescription = "Docente")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ingresar como Docente", fontSize = 18.sp)
        }
    }
}

@Composable
fun LoginEstudianteScreen(navController: NavController, viewModel: UsuarioViewModel) {
    var nombre by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(24.dp)
            .imePadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("¡Bienvenido!", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nombre,
                    onValueChange = {
                        nombre = it
                        error = false
                    },
                    label = { Text("Escribe tu nombre") },
                    isError = error,
                    modifier = Modifier.fillMaxWidth()
                )

                if (error) {
                    Text("Debes escribir tu nombre para jugar", color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (nombre.isNotBlank()) {
                            viewModel.nombreEstudianteActual = nombre // Guardamos el nombre en memoria
                            navController.navigate("home_estudiante")
                        } else {
                            error = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("Entrar a Jugar", fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(onClick = { navController.navigate("inicio") }) {
                    Text("Volver")
                }
            }
        }
    }
}

// 2. Pantalla de Login (Seguridad para el profesor)
@Composable
fun LoginDocenteScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp)
            .imePadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Acceso Restringido", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        error = false
                    },
                    label = { Text("Contraseña de Docente") },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = error
                )

                if (error) {
                    Text("Contraseña incorrecta", color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (password == "profesor123") {
                            navController.navigate("home_docente")
                        } else {
                            error = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text("Ingresar")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // NUEVO BOTÓN: Vía de escape para volver atrás
                OutlinedButton(
                    onClick = { navController.navigate("inicio") },
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text("Volver al Inicio")
                }
            }
        }
    }
}

// 3. Panel del Estudiante (UX adaptada para niños de 3.º Básico)
@Composable
fun HomeEstudianteScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color(0xFFE3F2FD), Color.White)))
            .padding(24.dp), // Margen amplio para que no se vea apretado
        verticalArrangement = Arrangement.SpaceEvenly, // Distribuye los elementos proporcionalmente
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "¡Hola, Estudiante! 🎒",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "¿Qué quieres hacer hoy? 🚀",
                style = MaterialTheme.typography.titleLarge
            )
        }

        // Botón principal de actividades
        Button(
            onClick = { navController.navigate("juego") }, // Conectado al minijuego
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp), // Altura masiva para facilitar el toque
            shape = RoundedCornerShape(24.dp), // Bordes infantiles y suaves
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Verde amigable
        ) {
            Text("🎮 Jugar y Aprender", fontSize = 24.sp)
        }

        // Botón secundario de progreso
        Button(
            onClick = { navController.navigate("progreso_estudiante") }, // Conectado al panel de progreso
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)) // Azul brillante
        ) {
            Text("🏆 Ver mi Progreso", fontSize = 24.sp)
        }

        // Vía de escape segura
        OutlinedButton(
            onClick = { navController.navigate("inicio") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Cerrar Sesión", fontSize = 18.sp)
        }
    }
}

// 4. Panel del Docente (Vista con privilegios y Base de Datos)
@Composable
fun HomeDocenteScreen(navController: NavController, viewModel: UsuarioViewModel) {
    // Observamos la lista de actividades desde Room en tiempo real
    val actividades by viewModel.actividades.collectAsStateWithLifecycle()
    val contextoLocal = LocalContext.current //Captura el contexto de Android para poder usar Toast y Archivos

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Panel del Docente", style = MaterialTheme.typography.headlineSmall)
            Button(onClick = { navController.navigate("inicio") }) {
                Text("Salir")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        Text("Registro de Actividades", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Aquí puedes monitorear el avance de tus estudiantes.")
        Spacer(modifier = Modifier.height(16.dp))

        // Lista reactiva que muestra los puntajes guardados en Room
        if (actividades.isEmpty()) {
            Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("Aún no hay actividades registradas")
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(actividades) { actividad ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Estudiante: ${actividad.estudianteNombre}", style = MaterialTheme.typography.titleMedium)
                            Text("Asignatura: ${actividad.asignatura}")
                            Text("Puntaje Obtenido: ${actividad.puntaje} pts", color = Color(0xFF2E7D32)) // Verde oscuro
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón conectado al Recurso Nativo (Descarga de archivo)
        Button(
            onClick = { generarArchivoReporte(contextoLocal, actividades) }, //Llama a la función nativa
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Descargar Reporte (Almacenamiento)", fontSize = 18.sp)
        }
    }
}



@Composable
fun JuegoScreen(navController: NavController, viewModel: UsuarioViewModel) {
    var numero1 by remember { mutableStateOf((1..9).random()) }
    var numero2 by remember { mutableStateOf((1..9).random()) }
    var respuesta by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var puntaje by remember { mutableStateOf(0) }

    val resultadoCorrecto = numero1 + numero2

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color(0xFFE3F2FD), Color.White)))
            .padding(24.dp)
            .imePadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Desafío de Matemáticas 🧮", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "¿Cuánto es $numero1 + $numero2?", 
            fontSize = 40.sp, 
            fontWeight = FontWeight.ExtraBold, 
            color = Color(0xFFE65100),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = respuesta,
            onValueChange = { respuesta = it },
            label = { Text("Tu respuesta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (respuesta == resultadoCorrecto.toString()) {
                    puntaje += 10
                    mensaje = "✨ ¡Correcto! Tienes $puntaje puntos ✨"

                    // Usa el nombre que guardamos en la pantalla de Login
                    viewModel.guardarActividad(viewModel.nombreEstudianteActual, "Matemáticas", 10, true)

                    numero1 = (1..9).random()
                    numero2 = (1..9).random()
                    respuesta = ""
                } else {
                    mensaje = "Inténtalo de nuevo"
                }
            },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Revisar", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(mensaje, fontSize = 20.sp, color = if (mensaje.contains("Correcto")) Color(0xFF4CAF50) else Color.Red)

        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(onClick = { navController.navigate("home_estudiante") }) {
            Text("Volver al Menú")
        }
    }
}
@Composable
fun ProgresoEstudianteScreen(navController: NavController, viewModel: UsuarioViewModel) {
    // Lee toda la base de datos
    val actividades by viewModel.actividades.collectAsStateWithLifecycle()

    // Filtra solo las de este estudiante y suma el puntaje
    val misActividades = actividades.filter { it.estudianteNombre == viewModel.nombreEstudianteActual }
    val puntajeTotal = misActividades.sumOf { it.puntaje }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mi Progreso", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Estudiante: ${viewModel.nombreEstudianteActual}", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(32.dp))

        // Tarjeta de resumen
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)) // Azul muy clarito
        ) {
            Column(
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Puntaje Total", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("$puntajeTotal pts", fontSize = 40.sp, color = Color(0xFF2196F3))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text("Mis medallas:", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(16.dp))

        // Lista de las partidas jugadas
        if (misActividades.isEmpty()) {
            Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("Aún no tienes medallas")
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(misActividades) { actividad ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(actividad.asignatura, fontSize = 18.sp)
                            Text("+${actividad.puntaje} pts", fontSize = 18.sp, color = Color(0xFF4CAF50))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { navController.navigate("home_estudiante") },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Volver al Menú", fontSize = 20.sp)
        }
    }
}

// Función que utiliza el almacenamiento nativo del dispositivo
fun generarArchivoReporte(context: Context, actividades: List<cl.duoc.mpazpro.data.Actividad>) {
    try {
        // Accede a la carpeta nativa de Descargas del celular
        val directorioDescargas = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val archivo = File(directorioDescargas, "Reporte_Estudiantes_MPAZPRO.txt")

        // Construye el texto del informe
        var contenido = "=== REPORTE DE RENDIMIENTO MPAZ PRO ===\n\n"
        actividades.forEach {
            contenido += "Alumno: ${it.estudianteNombre} | Asignatura: ${it.asignatura} | Puntaje: ${it.puntaje}\n"
        }

        // Escribe el archivo en el almacenamiento
        archivo.writeText(contenido)

        // Muestra un mensaje nativo (Toast) confirmando el éxito
        Toast.makeText(context, "¡Reporte guardado en Descargas!", Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
    }
}
