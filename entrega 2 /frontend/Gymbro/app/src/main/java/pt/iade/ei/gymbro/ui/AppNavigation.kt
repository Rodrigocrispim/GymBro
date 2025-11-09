package pt.iade.ei.gymbro.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import pt.iade.ei.gymbro.ui.screens.home.HomeScreen

import pt.iade.ei.gymbro.ui.screens.create_offer.CreateOfferScreen

import pt.iade.ei.gymbro.ui.screens.login.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "home") {


        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }


        composable("register") {

            Text("Aqui será o Ecrã de Registo")
        }


        composable("home") {
            HomeScreen(
                onNavigateToCreateOffer = {

                    navController.navigate("create_offer")
                },
                onNavigateToApplications = {

                    navController.navigate("applications")
                }
            )
        }


        composable("create_offer") {
            CreateOfferScreen(
                onOfferCreated = {

                    navController.navigate("home") {
                        popUpTo("create_offer") { inclusive = true }
                    }
                }
            )
        }


        composable("applications") {

            Text("Aqui será o Ecrã de Candidaturas")
        }
    }
}