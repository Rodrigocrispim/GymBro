package pt.iade.ei.gymbro.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.iade.ei.gymbro.R
import pt.iade.ei.gymbro.data.network.OfferResponse
import pt.iade.ei.gymbro.ui.utils.SessionManager


val GymBackground = Color(0xFF0F1115)
val GymGreen = Color(0xFF00C853)
val GymYellow = Color(0xFFFFD600)
val GymRed = Color(0xFFFF1744)
val GymInputColor = Color(0xFF2A3744)
val GymBlue = Color(0xFF2196F3)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToCreateOffer: () -> Unit,
    onNavigateToApplications: (Int) -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToMyApps: () -> Unit
) {
    val viewModel: HomeScreenViewModel = viewModel()
    val context = LocalContext.current


    var searchQuery by remember { mutableStateOf("") }


    var showApplyDialog by remember { mutableStateOf(false) }
    var selectedOfferId by remember { mutableStateOf<Int?>(null) }
    var applyComment by remember { mutableStateOf("") }


    LaunchedEffect(key1 = true) {
        viewModel.loadOffers()
    }

    Scaffold(
        containerColor = GymBackground,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.Black
            ) {

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Offers") },
                    selected = true,
                    onClick = {  },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = GymGreen, selectedTextColor = GymGreen, indicatorColor = Color.White)
                )


                NavigationBarItem(
                    icon = {
                        Box(modifier = Modifier.size(48.dp).background(GymGreen, CircleShape), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Add, contentDescription = "Create", tint = Color.White)
                        }
                    },
                    label = { Text("") },
                    selected = false,
                    onClick = onNavigateToCreateOffer,
                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
                )


                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Apps") },
                    label = { Text("My Apps") },
                    selected = false,
                    onClick = onNavigateToMyApps,
                    colors = NavigationBarItemDefaults.colors(unselectedIconColor = Color.Gray)
                )
            }
        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {


            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.img), contentDescription = "Logo", modifier = Modifier.height(40.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Workout offers", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text("Find your next workout partner", color = Color.Gray, fontSize = 14.sp)
                }
                IconButton(onClick = onNavigateToProfile) {
                    Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White)
                }
            }


            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search...", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = GymInputColor, unfocusedContainerColor = GymInputColor,
                    focusedTextColor = Color.White, unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de Ofertas
            if (viewModel.isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator(color = GymGreen) }
            } else if (viewModel.errorMessage != null) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Erro: ${viewModel.errorMessage}", color = Color.Red) }
            } else {
                LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                    items(viewModel.offers) { offer ->
                        OfferCard(
                            offer = offer,
                            onApplyClick = {

                                selectedOfferId = offer.id
                                showApplyDialog = true
                            },
                            onSeeApplicationsClick = {

                                onNavigateToApplications(offer.id)
                            }
                        )
                    }
                }
            }
        }


        if (showApplyDialog) {
            AlertDialog(
                onDismissRequest = { showApplyDialog = false },
                containerColor = GymInputColor,
                title = { Text("Join Workout", color = Color.White, fontWeight = FontWeight.Bold) },
                text = {
                    Column {
                        Text("Send a message to the host:", color = Color.Gray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = applyComment,
                            onValueChange = { applyComment = it },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedContainerColor = Color.Black.copy(alpha = 0.3f),
                                unfocusedContainerColor = Color.Black.copy(alpha = 0.3f),
                                focusedBorderColor = GymGreen,
                                unfocusedBorderColor = Color.Gray
                            ),
                            placeholder = { Text("Ex: Consigo ir a essa hora!", color = Color.Gray) }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (selectedOfferId != null && applyComment.isNotBlank()) {

                                viewModel.applyToOffer(selectedOfferId!!, applyComment) { success ->
                                    if (success) {
                                        Toast.makeText(context, "Candidatura enviada!", Toast.LENGTH_SHORT).show()
                                        showApplyDialog = false
                                        applyComment = ""
                                    } else {
                                        Toast.makeText(context, "Erro ao enviar candidatura", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                Toast.makeText(context, "Escreve uma mensagem primeiro", Toast.LENGTH_SHORT).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = GymGreen)
                    ) {
                        Text("Send Request", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showApplyDialog = false }) {
                        Text("Cancel", color = Color.White)
                    }
                }
            )
        }
    }
}


@Composable
fun OfferCard(
    offer: OfferResponse,
    onApplyClick: () -> Unit,
    onSeeApplicationsClick: () -> Unit
) {
    val titleDisplay = if (!offer.title.isNullOrBlank()) offer.title else "Treino Sem Título"
    val creatorName = if (!offer.userName.isNullOrBlank()) offer.userName else "GymBro User"
    val locationDisplay = offer.locationName ?: "Localização desconhecida"

    val timeDisplay = if (offer.weekday != null && offer.timePeriod != null) {
        "${offer.weekday} • ${offer.timePeriod}"
    } else {
        offer.weekday ?: offer.timePeriod ?: "Horário a combinar"
    }

    val initial = creatorName?.firstOrNull()?.uppercase() ?: "?"

    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GymInputColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Cabeçalho
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(titleDisplay, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Place, null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                        Text(locationDisplay, color = Color.Gray, fontSize = 13.sp)
                    }
                }
                Box(modifier = Modifier.size(40.dp).background(Color.LightGray, CircleShape), contentAlignment = Alignment.Center) {
                    Text(initial, color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("By $creatorName", color = GymGreen, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))

            // Badges
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                GymBadge(offer.workoutTypeName ?: "Treino", Color(0xFF5E6A75), Color.White)

                val levelName = offer.levelName ?: "Nível"
                val levelColor = when (levelName.lowercase()) {
                    "avançado", "advanced" -> GymRed
                    "intermédio", "intermediate" -> GymYellow
                    else -> GymGreen
                }
                GymBadge(levelName, levelColor.copy(alpha = 0.2f), levelColor)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Icon(Icons.Default.Schedule, null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(timeDisplay, color = Color(0xFFB0B3B8), fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))


            if (offer.userId == SessionManager.userId) {

                Button(
                    onClick = onSeeApplicationsClick,
                    modifier = Modifier.fillMaxWidth().height(45.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GymBlue),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.List, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ver Candidaturas", color = Color.White, fontWeight = FontWeight.Bold)
                }
            } else {

                Button(
                    onClick = onApplyClick,
                    modifier = Modifier.fillMaxWidth().height(45.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GymGreen),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Apply to Join", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun GymBadge(text: String, color: Color, textColor: Color) {
    Box(
        modifier = Modifier.background(color, RoundedCornerShape(16.dp)).padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text, color = textColor, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}