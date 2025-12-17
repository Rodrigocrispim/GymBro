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


import pt.iade.ei.gymbro.ui.screens.applications.UserApplicationsScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val CREATE_OFFER = "create_offer"
    const val APPLICATIONS = "applications"
    const val MY_APPS = "my_apps"
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


        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToCreateOffer = { navController.navigate(Routes.CREATE_OFFER) },


                onNavigateToApplications = { ofertaId ->
                    navController.navigate("${Routes.APPLICATIONS}/$ofertaId")
                },


                onNavigateToMyApps = {
                    navController.navigate(Routes.MY_APPS)
                },

                onNavigateToProfile = { navController.navigate(Routes.PROFILE) }
            )
        }


        composable(Routes.CREATE_OFFER) {
            CreateOfferScreen(
                onNavigateBack = { navController.popBackStack() },
                onOfferCreated = {
                    navController.popBackStack()
                }
            )
        }


        composable("${Routes.APPLICATIONS}/{ofertaId}") { backStackEntry ->
            val ofertaIdString = backStackEntry.arguments?.getString("ofertaId")
            val ofertaId = ofertaIdString?.toIntOrNull() ?: 0

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


        composable(Routes.MY_APPS) {
            UserApplicationsScreen(
                onNavigateBack = { navController.popBackStack() },


                onNavigateToManage = { ofertaId ->
                    navController.navigate("${Routes.APPLICATIONS}/$ofertaId")
                }
            )
        }


        composable(Routes.PROFILE) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogout = {
                    SessionManager.clearSession()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )
        }
    }
}