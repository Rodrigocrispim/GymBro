package pt.iade.ei.gymbro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pt.iade.ei.gymbro.ui.AppNavigation
import pt.iade.ei.gymbro.ui.theme.GymbroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymbroTheme {


                AppNavigation()

            }
        }
    }
}