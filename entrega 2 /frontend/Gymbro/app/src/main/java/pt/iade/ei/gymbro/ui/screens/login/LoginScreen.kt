package pt.iade.ei.gymbro.ui.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gymbro.R
import pt.iade.ei.gymbro.ui.theme.GymbroTheme

// MUDAR CORES
val GymBroGreen = Color(0xFF30D158)
val GymBroBlack = Color(0xFF1C1C1E)
val GymBroGray = Color(0xFF8A8A8E)


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = uiState.loginSuccess) {
        if (uiState.loginSuccess) {
            onLoginSuccess()
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
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id= R.drawable.img),contentDescription = "Logo",
                    modifier = Modifier.size(200.dp))
                Text("GymBro", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text("Don't have an account? ", color = GymBroGray)
                    Text(
                        "Sign Up",
                        color = GymBroGreen,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            if (!uiState.isLoading) onNavigateToRegister()
                        }
                    )
                }
                Spacer(modifier = Modifier.height(48.dp))

                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Default.Email, "Email", tint = GymBroGray) },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = GymBroGray,
                        unfocusedLabelColor = GymBroGray,
                        focusedIndicatorColor = GymBroGreen,
                        unfocusedIndicatorColor = GymBroGray,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        cursorColor = GymBroGreen
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    isError = uiState.error != null,
                    readOnly = uiState.isLoading
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, "Password", tint = GymBroGray) },
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(image, "Toggle password", tint = GymBroGray)
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = GymBroGray,
                        unfocusedLabelColor = GymBroGray,
                        focusedIndicatorColor = GymBroGreen,
                        unfocusedIndicatorColor = GymBroGray,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        cursorColor = GymBroGreen
                    ),
                    isError = uiState.error != null,
                    readOnly = uiState.isLoading
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Forgot Your Password?",
                    color = GymBroGray,
                    modifier = Modifier.fillMaxWidth().clickable { /* TODO */ },
                    textAlign = TextAlign.End
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { viewModel.onLoginClicked() },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GymBroGreen, contentColor = Color.Black),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !uiState.isLoading
                ) {
                    Text("Log In", fontSize = 16.sp, fontWeight = FontWeight.Bold)
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



@Preview(showBackground = true, backgroundColor = 0xFF1C1C1E)
@Composable
fun LoginScreenPreview() {
    GymbroTheme {
        LoginScreen(
            onLoginSuccess = {},
            onNavigateToRegister = {}
        )
    }
}