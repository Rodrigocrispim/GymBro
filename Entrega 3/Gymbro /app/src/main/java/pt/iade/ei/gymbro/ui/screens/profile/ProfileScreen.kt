package pt.iade.ei.gymbro.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

// Cores
private val GymBackground = Color(0xFF0F1115)
private val GymCardColor = Color(0xFF1E2633)
private val GymInputColor = Color(0xFF2A3744)
private val GymGreen = Color(0xFF00C853)
private val GymRed = Color(0xFFFF1744)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit
) {
    val viewModel: ProfileViewModel = viewModel()


    val fullName = viewModel.fullName
    val email = viewModel.email
    val bio = viewModel.bio
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage


    val locationText = viewModel.locationId?.toString().orEmpty()
    val levelText = viewModel.levelId?.toString().orEmpty()


    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    Scaffold(
        containerColor = GymBackground,
        topBar = {
            TopAppBar(
                title = { Text("Profile", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = GymBackground)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            if (isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    color = GymGreen
                )
            }

            if (error != null) {

                Text(
                    text = "Erro: $error",
                    color = GymRed,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }


            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = GymCardColor),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(96.dp)
                                .background(GymInputColor, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {

                            if (fullName.isNotBlank()) {

                                Text(
                                    text = fullName.take(1).uppercase(),
                                    color = GymGreen,
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            } else {

                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.size(56.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { /* TODO: abrir picker de foto e enviar urlFotoPerfil */ },
                            colors = ButtonDefaults.buttonColors(containerColor = GymGreen),
                            shape = RoundedCornerShape(50),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Change Photo", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = GymCardColor),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Personal Information",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))


                        Text("Full Name", color = Color.Gray, fontSize = 12.sp)
                        Spacer(Modifier.height(4.dp))
                        TextField(
                            value = fullName,
                            onValueChange = { viewModel.fullName = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = GymInputColor,
                                unfocusedContainerColor = GymInputColor,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))


                        Text("Email", color = Color.Gray, fontSize = 12.sp)
                        Spacer(Modifier.height(4.dp))
                        TextField(
                            value = email,
                            onValueChange = { },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = false,
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                disabledContainerColor = GymInputColor,
                                disabledTextColor = Color.LightGray,
                                disabledIndicatorColor = Color.Transparent,
                                focusedContainerColor = GymInputColor,
                                unfocusedContainerColor = GymInputColor
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))


                        Text("Location ID (Teste)", color = Color.Gray, fontSize = 12.sp)
                        Spacer(Modifier.height(4.dp))
                        TextField(
                            value = locationText,
                            onValueChange = { txt ->
                                viewModel.locationId = txt.toIntOrNull()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = GymInputColor,
                                unfocusedContainerColor = GymInputColor,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }
                }


                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = GymCardColor),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Fitness Profile",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))


                        Text("Fitness Level ID (Teste)", color = Color.Gray, fontSize = 12.sp)
                        Spacer(Modifier.height(4.dp))
                        TextField(
                            value = levelText,
                            onValueChange = { txt ->
                                viewModel.levelId = txt.toIntOrNull()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            placeholder = { Text("Level Id") },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = GymInputColor,
                                unfocusedContainerColor = GymInputColor,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))


                        Text("Bio", color = Color.Gray, fontSize = 12.sp)
                        Spacer(Modifier.height(4.dp))
                        TextField(
                            value = bio,
                            onValueChange = { viewModel.bio = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            singleLine = false,
                            placeholder = { Text("Describe your goals") },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = GymInputColor,
                                unfocusedContainerColor = GymInputColor,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = {
                    viewModel.saveProfile { /* podes mostrar Toast se quiseres */ }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GymGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Save Profile", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))


            Button(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GymRed),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("LOGOUT", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}