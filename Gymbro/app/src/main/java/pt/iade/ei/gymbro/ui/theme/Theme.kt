package pt.iade.ei.gymbro.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect // <--- Importante
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb // <--- Importante
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView // <--- Importante
import androidx.core.view.WindowCompat // <--- Importante

// Configuração do Tema Escuro
private val DarkColorScheme = darkColorScheme(
    primary = GymBroGreen,
    secondary = GymBroGray,
    tertiary = Pink80,
    background = GymBroBlack,
    surface = GymBroDarkBlue,
    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

// Configuração do Tema Claro (Igual ao escuro para manter a identidade)
private val LightColorScheme = lightColorScheme(
    primary = GymBroGreen,
    secondary = GymBroGray,
    tertiary = Pink40,
    background = GymBroBlack,
    surface = GymBroDarkBlue,
    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun GymbroTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Desligado para manter as cores da marca
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // --- A MAGIA ACONTECE AQUI ---
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            // 1. Define a cor da barra lá em cima como PRETA (GymBroBlack)
            window.statusBarColor = GymBroBlack.toArgb()

            // 2. Define que os ícones devem ser BRANCOS
            // (isAppearanceLightStatusBars = false significa "Não quero ícones escuros")
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }
    // -----------------------------

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}