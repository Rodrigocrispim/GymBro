package pt.iade.ei.gymbro.ui.screens.create_offer


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import pt.iade.ei.gymbro.ui.screens.login.GymBroBlack
import pt.iade.ei.gymbro.ui.screens.login.GymBroGray
import pt.iade.ei.gymbro.ui.screens.login.GymBroGreen

import pt.iade.ei.gymbro.ui.theme.GymbroTheme


@Composable
fun CreateOfferScreen(
    viewModel: CreateOfferViewModel = viewModel(),
    onOfferCreated: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()


    LaunchedEffect(key1 = uiState.createSuccess) {
        if (uiState.createSuccess) {
            onOfferCreated()
            viewModel.onNavigationDone()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = GymBroBlack
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {

                Text(
                    "Create offers",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))


                Text("Workout title", color = GymBroGray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                FormTextField(
                    value = uiState.title,
                    onValueChange = viewModel::onTitleChange,
                    placeholder = "e.g.: Morning Strength Training"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Location", color = GymBroGray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                FormTextField(
                    value = uiState.location,
                    onValueChange = viewModel::onLocationChange,
                    placeholder = "Gym name or Address"
                )

                Spacer(modifier = Modifier.height(16.dp))


                Row(Modifier.fillMaxWidth()) {
                    Column(Modifier.weight(1f)) {
                        Text("Date", color = GymBroGray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        FormTextField(
                            value = uiState.date,
                            onValueChange = viewModel::onDateChange,
                            placeholder = "01/01/2025"
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(Modifier.weight(1f)) {
                        Text("Time", color = GymBroGray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        FormTextField(
                            value = uiState.time,
                            onValueChange = viewModel::onTimeChange,
                            placeholder = "12:50"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Workout type", color = GymBroGray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                FormTextField(
                    value = uiState.workoutType,
                    onValueChange = viewModel::onWorkoutTypeChange,
                    placeholder = "Select workout type"
                )

                Spacer(modifier = Modifier.height(16.dp))


                Row(Modifier.fillMaxWidth()) {
                    Column(Modifier.weight(1f)) {
                        Text("Level Required", color = GymBroGray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        FormTextField(
                            value = uiState.levelRequired,
                            onValueChange = viewModel::onLevelChange,
                            placeholder = "Any Level"
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(Modifier.weight(1f)) {
                        Text("Max People", color = GymBroGray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        FormTextField(
                            value = uiState.maxPeople,
                            onValueChange = viewModel::onMaxPeopleChange,
                            placeholder = "1-10",
                            keyboardType = KeyboardType.Number // Teclado numérico
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Description", color = GymBroGray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                FormTextField(
                    value = uiState.description,
                    onValueChange = viewModel::onDescriptionChange,
                    placeholder = "Describe your workout session...",
                    singleLine = false,
                    minLines = 4
                )

                Spacer(modifier = Modifier.height(32.dp))


                Button(
                    onClick = { viewModel.onCreateOfferClicked() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GymBroGreen,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !uiState.isLoading
                ) {
                    Text(
                        "Create Offer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                uiState.error?.let { errorText ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = errorText,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }


        if (uiState.isLoading) {
            CircularProgressIndicator(color = GymBroGreen)
        }
    }
}


@Composable
private fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean = true,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder, color = GymBroGray) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF2C2C2E),
            unfocusedContainerColor = Color(0xFF2C2C2E),
            focusedIndicatorColor = GymBroGreen,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = GymBroGreen,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = singleLine,
        minLines = minLines,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}


@Preview(showBackground = true, backgroundColor = 0xFF1C1C1E)
@Composable
fun CreateOfferScreenPreview() {
    GymbroTheme {
        CreateOfferScreen(
            onOfferCreated = {}
        )
    }
}