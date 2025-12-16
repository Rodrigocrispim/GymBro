package pt.iade.ei.gymbro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
// ⚠️ CORREÇÃO: O AppNavigation está dentro de 'ui.screens', então o import muda:
import pt.iade.ei.gymbro.ui.screens.AppNavigation
import pt.iade.ei.gymbro.ui.utils.SessionManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inicializar o Gestor de Sessão
        SessionManager.init(this)

        setContent {
            // 2. Arrancar a Navegação
            AppNavigation()
        }
    }
}