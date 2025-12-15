package pt.iade.ei.gymbro.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.gymbro.ui.screens.create_offer.CreateOfferScreen
import pt.iade.ei.gymbro.ui.screens.home.HomeScreen
import pt.iade.ei.gymbro.ui.screens.login.LoginScreen
import pt.iade.ei.gymbro.ui.screens.profile.ProfileScreen
import pt.iade.ei.gymbro.ui.screens.register.RegisterScreen
import pt.iade.ei.gymbro.ui.utils.SessionManager
// Certifica-te que este import está presente:
import pt.iade.ei.gymbro.ui.screens.ApplicationsScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val CREATE_OFFER = "create_offer"
    const val APPLICATIONS = "applications" // Base da rota
    const val PROFILE = "profile"
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val startDestination = if (SessionManager.isLoggedIn()) Routes.HOME else Routes.LOGIN

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // --- LOGIN ---
        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Routes.REGISTER) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        // --- REGISTER ---
        composable(Routes.REGISTER) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                }
            )
        }

        // --- HOME ---
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToCreateOffer = { navController.navigate(Routes.CREATE_OFFER) },
                // NOTA: Mais tarde terás de alterar isto na HomeScreen para passar o ID:
                // Exemplo: onNavigateToApplications = { id -> navController.navigate("applications/$id") }
                onNavigateToApplications = {
                    // Por enquanto, para não dar erro, vamos assumir um ID 0 ou de teste se clicares sem ID
                    navController.navigate("${Routes.APPLICATIONS}/0")
                },
                onNavigateToProfile = { navController.navigate(Routes.PROFILE) }
            )
        }

        // --- CREATE OFFER ---
        composable(Routes.CREATE_OFFER) {
            CreateOfferScreen(
                onNavigateBack = { navController.popBackStack() },
                onOfferCreated = {
                    navController.popBackStack()
                }
            )
        }

        // --- APPLICATIONS ---
        // Aqui definimos que a rota espera um argumento {ofertaId}
        composable("${Routes.APPLICATIONS}/{ofertaId}") { backStackEntry ->
            // 1. Recuperar o ID passado na navegação
            val ofertaIdString = backStackEntry.arguments?.getString("ofertaId")
            val ofertaId = ofertaIdString?.toIntOrNull() ?: 0

            // 2. Chamar o ecrã com os parâmetros que faltavam (ofertaId e onLogout)
            ApplicationsScreen(
                ofertaId = ofertaId,
                onNavigateBack = { navController.popBackStack() },
                onLogout = {
                    SessionManager.clearSession()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )
        }

        // --- PROFILE ---
        composable(Routes.PROFILE) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogout = {
                    // 1. Limpar os dados da sessão
                    SessionManager.clearSession()

                    // 2. Navegar para o Login e limpar histórico
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )
        }
    }
}