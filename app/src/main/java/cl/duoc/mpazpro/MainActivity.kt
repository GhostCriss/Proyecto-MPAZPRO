package cl.duoc.mpazpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cl.duoc.mpazpro.navigation.AppNavigation // Importamos tu archivo de rutas
import cl.duoc.mpazpro.ui.theme.MPAZPROTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MPAZPROTheme {
                // Surface actúa como el lienzo en blanco que ocupa toda la pantalla
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // AQUÍ ESTÁ LA MAGIA: Llamamos a tu sistema de navegación en vez del saludo
                    AppNavigation()
                }
            }
        }
    }
}