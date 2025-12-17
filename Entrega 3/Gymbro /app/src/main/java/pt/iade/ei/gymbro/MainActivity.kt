package pt.iade.ei.gymbro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import pt.iade.ei.gymbro.ui.screens.AppNavigation
import pt.iade.ei.gymbro.ui.utils.SessionManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        SessionManager.init(this)

        setContent {

            AppNavigation()
        }
    }
}