
package pt.iade.ei.gymbro.ui.screens.create_offer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


val GymBackground = Color(0xFF0F1115)
val GymInputColor = Color(0xFF2A3744)
val GymGreen = Color(0xFF00C853)

val daysList = listOf(
    "Segunda" to 1, "Terça" to 2, "Quarta" to 3, "Quinta" to 4,
    "Sexta" to 5, "Sábado" to 6, "Domingo" to 7
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOfferScreen(
    onNavigateBack: () -> Unit,
    onOfferCreated: () -> Unit
) {
    val viewModel: CreateOfferViewModel = viewModel()
    val scrollState = rememberScrollState()


    val locationOptions = remember(viewModel.locations) { viewModel.locations.map { it.label to it.id } }
    val typeOptions = remember(viewModel.workoutTypes) { viewModel.workoutTypes.map { it.label to it.id } }
    val levelOptions = remember(viewModel.levels) { viewModel.levels.map { it.label to it.id } }
    val periodOptions = remember(viewModel.periods) { viewModel.periods.map { it.label to it.id } }

    val isFormValid = viewModel.title.isNotBlank() &&
            viewModel.description.isNotBlank() &&
            viewModel.selectedLocationId > 0 &&
            viewModel.selectedLevelId > 0 &&
            viewModel.selectedTypeId > 0 &&
            viewModel.selectedTimePeriodId > 0

    Scaffold(
        containerColor = GymBackground,
        topBar = {
            TopAppBar(
                title = { Text("Create Offer", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
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
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(16.dp))


            GymLabel("Workout Title")
            GymTextField(
                value = viewModel.title,
                onValueChange = { viewModel.title = it },
                placeholder = "Ex: Morning Strength Training"
            )

            Spacer(modifier = Modifier.height(16.dp))


            GymLabel("Location")
            if (locationOptions.isNotEmpty()) {
                GymDropdown(
                    options = locationOptions,
                    selectedId = viewModel.selectedLocationId,
                    onOptionSelected = { viewModel.selectedLocationId = it }
                )
            } else {
                Text(if(viewModel.isLoading) "Loading..." else "No locations found", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    GymLabel("Day of Week")
                    GymDropdown(
                        options = daysList,
                        selectedId = viewModel.selectedWeekdayId,
                        onOptionSelected = { viewModel.selectedWeekdayId = it }
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    GymLabel("Time of Day")
                    if (periodOptions.isNotEmpty()) {
                        GymDropdown(
                            options = periodOptions,
                            selectedId = viewModel.selectedTimePeriodId,
                            onOptionSelected = { viewModel.selectedTimePeriodId = it }
                        )
                    } else {
                        Text("Loading...", color = Color.Gray, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            GymLabel("Workout Type")
            if (typeOptions.isNotEmpty()) {
                GymDropdown(
                    options = typeOptions,
                    selectedId = viewModel.selectedTypeId,
                    onOptionSelected = { viewModel.selectedTypeId = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            GymLabel("Level Required")
            if (levelOptions.isNotEmpty()) {
                GymDropdown(
                    options = levelOptions,
                    selectedId = viewModel.selectedLevelId,
                    onOptionSelected = { viewModel.selectedLevelId = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            GymLabel("Description")
            GymTextField(
                value = viewModel.description,
                onValueChange = { viewModel.description = it },
                placeholder = "Describe your workout session...",
                isSingleLine = false,
                height = 100.dp
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (viewModel.errorMessage != null) {
                Text(text = viewModel.errorMessage!!, color = Color.Red, fontSize = 14.sp)
            }

            Button(
                onClick = { if (isFormValid) viewModel.createOffer(onSuccess = onOfferCreated) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GymGreen),
                shape = RoundedCornerShape(8.dp),
                enabled = !viewModel.isLoading && isFormValid
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Create Offer", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 16.sp)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
fun GymLabel(text: String) {
    Text(text = text, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
}

@Composable
fun GymTextField(value: String?, onValueChange: (String) -> Unit, placeholder: String, isSingleLine: Boolean = true, height: androidx.compose.ui.unit.Dp? = null) {
    val safeValue = value ?: ""
    OutlinedTextField(
        value = safeValue,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth().let { if (height != null) it.height(height) else it },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = GymInputColor, unfocusedContainerColor = GymInputColor,
            focusedTextColor = Color.White, unfocusedTextColor = Color.White,
            focusedBorderColor = GymGreen, unfocusedBorderColor = Color.Transparent
        ),
        singleLine = isSingleLine,
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun GymDropdown(options: List<Pair<String, Int>>, selectedId: Int, onOptionSelected: (Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val selectedText = options.find { it.second == selectedId }?.first ?: "Selecionar"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(GymInputColor, RoundedCornerShape(8.dp))
            .clickable(enabled = options.isNotEmpty()) { expanded = true }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = selectedText, color = Color.White)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF2A3744))
        ) {
            options.forEach { (name, id) ->
                DropdownMenuItem(
                    text = { Text(text = name, color = Color.White) },
                    onClick = { onOptionSelected(id); expanded = false }
                )
            }
        }
    }
}