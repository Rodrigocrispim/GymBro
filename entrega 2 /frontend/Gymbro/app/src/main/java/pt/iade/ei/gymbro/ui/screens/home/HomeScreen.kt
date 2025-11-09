package pt.iade.ei.gymbro.ui.screens.home

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gymbro.R

import pt.iade.ei.gymbro.data.network.OfferResponse
import pt.iade.ei.gymbro.ui.screens.login.GymBroBlack
import pt.iade.ei.gymbro.ui.screens.login.GymBroGray
import pt.iade.ei.gymbro.ui.screens.login.GymBroGreen
import pt.iade.ei.gymbro.ui.theme.GymbroTheme


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel(),
    onNavigateToCreateOffer: () -> Unit,
    onNavigateToApplications: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = {
            HomeBottomBar(
                onNavigateToCreateOffer = onNavigateToCreateOffer,
                onNavigateToApplications = onNavigateToApplications
            )
        },
        containerColor = GymBroBlack
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    SearchBar()
                    Spacer(modifier = Modifier.height(16.dp))
                }


                items(uiState.offerList) { offer ->
                    OfferCard(
                        offer = offer,
                        onApplyClicked = { }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            if (uiState.isLoading) {
                CircularProgressIndicator(color = GymBroGreen)
            }

            uiState.error?.let { errorText ->
                Text(
                    text = errorText,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun HomeTopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(GymBroBlack)
            .padding(start = 16.dp, end = 16.dp, top = 48.dp, bottom = 16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "GymBro Logo",
                modifier = Modifier.size(50.dp).align(Alignment.Center)
            )
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier.size(40.dp).clip(CircleShape).background(GymBroGray).padding(8.dp).align(Alignment.CenterEnd)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Workout offers", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Text("Find your next workout partner", fontSize = 16.sp, color = GymBroGray)
    }
}


@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Search workouts, locations, or types", color = GymBroGray) },
        leadingIcon = { Icon(Icons.Default.Search, "Search", tint = GymBroGray) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF2C2C2E),
            unfocusedContainerColor = Color(0xFF2C2C2E),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = GymBroGreen,
        ),
        shape = RoundedCornerShape(12.dp)
    )
}


@Composable
fun OfferCard(

    offer: OfferResponse,
    onApplyClicked: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF263238)) // O teu azul
    ) {
        Column(Modifier.padding(16.dp)) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            ) {

                Text(
                    text = offer.titulo,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(end = 42.dp)
                )

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(GymBroGray)
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = offer.nomeCriador.firstOrNull()?.toString() ?: "?",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }


            Text(offer.localizacaoNome, fontSize = 14.sp, color = GymBroGray)

            Text("By ${offer.nomeCriador}", fontSize = 14.sp, color = Color.White)


            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(vertical = 8.dp)) {


                val tags = listOf(offer.tipoTreinoNome, offer.nivelNome)

                tags.forEach { tag ->
                    val (backgroundColor, textColor) = when (tag.lowercase()) {
                        "intermédio" -> Color(0xFF333333) to Color.Yellow // Fundo escuro, Texto Amarelo
                        "iniciante" -> Color(0xFF333333) to GymBroGreen // Fundo escuro, Texto Verde
                        "advanced" -> Color(0xFF333333) to Color.Red     // Fundo escuro, Texto Vermelho
                        else -> GymBroGray.copy(alpha = 0.2f) to GymBroGray.copy(alpha = 0.8f) // Outros (ex: "Push", "Pull")
                    }

                    Text(
                        text = tag,
                        color = textColor,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .background(
                                color = backgroundColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }



            Spacer(modifier = Modifier.height(8.dp))


            Text(offer.descricao, fontSize = 14.sp, color = Color.White, maxLines = 2)

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onApplyClicked,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GymBroGreen),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Apply to Join", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Composable
fun HomeBottomBar(
    onNavigateToCreateOffer: () -> Unit,
    onNavigateToApplications: () -> Unit
) {

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { /* Já estamos aqui */ },
            icon = { Icon(Icons.Default.List, "Offers", tint = GymBroGreen) },
            label = { Text("Offers", color = GymBroGreen, fontWeight = FontWeight.Bold) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            FloatingActionButton(
                onClick = onNavigateToCreateOffer,
                shape = CircleShape,
                containerColor = GymBroGreen,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(Icons.Default.Add, "Create Offer", tint = Color.Black)
            }
        }
        NavigationBarItem(
            selected = false,
            onClick = onNavigateToApplications,
            icon = { Icon(Icons.Default.Star, "Applications", tint = GymBroGray) },
            label = { Text("Applications", color = GymBroGray) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    GymbroTheme {

        /*
        HomeScreen(
            onNavigateToCreateOffer = {},
            onNavigateToApplications = {}
        )
        */
    }
}