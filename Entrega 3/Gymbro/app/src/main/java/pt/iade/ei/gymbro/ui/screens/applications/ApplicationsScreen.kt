package pt.iade.ei.gymbro.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gymbro.data.network.RetrofitInstance
import pt.iade.ei.gymbro.data.network.ApplicationResponse
import pt.iade.ei.gymbro.data.network.UpdateStatusRequest
import pt.iade.ei.gymbro.ui.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Cores
val GymBackground = Color(0xFF121212)
val GymCardColor = Color(0xFF2C3E50)
val GymGreen = Color(0xFF58B970)
val GymRed = Color(0xFFD32F2F)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationsScreen(
    ofertaId: Int,
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current

    var candidaturas by remember { mutableStateOf<List<ApplicationResponse>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun loadApplications() {
        // Logs da sessão
        Log.d("SESSION", "Token atual: ${SessionManager.token}")
        Log.d("SESSION", "UserId atual: ${SessionManager.userId}")

        isLoading = true
        val call = RetrofitInstance.api.getOfferApplications(ofertaId)

        call.enqueue(object : Callback<List<ApplicationResponse>> {
            override fun onResponse(
                call: Call<List<ApplicationResponse>>,
                response: Response<List<ApplicationResponse>>
            ) {
                Log.w(
                    "SESSION",
                    "Resposta candidaturas: code=${response.code()} message=${response.message()}"
                )

                isLoading = false
                if (response.isSuccessful) {
                    candidaturas = response.body() ?: emptyList()
                } else {
                    // Mostra mensagem mas NÃO faz logout
                    if (response.code() == 401 || response.code() == 403) {
                        errorMessage = "Sem permissão para ver as candidaturas (${response.code()})"
                    } else {
                        errorMessage = "Erro ao carregar: ${response.code()}"
                    }
                }

            }

            override fun onFailure(
                call: Call<List<ApplicationResponse>>,
                t: Throwable
            ) {
                isLoading = false
                errorMessage = "Erro de rede: ${t.message}"
            }
        })
    }

    fun updateStatus(id: Int, newStatus: String) {
        val request = UpdateStatusRequest(status = newStatus)

        RetrofitInstance.api.updateApplicationStatus(id, request)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Status atualizado!", Toast.LENGTH_SHORT).show()
                        loadApplications()
                    } else {
                        Toast.makeText(context, "Erro ao atualizar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(context, "Erro de rede", Toast.LENGTH_SHORT).show()
                }
            })
    }

    LaunchedEffect(ofertaId) {
        loadApplications()
    }

    Scaffold(
        containerColor = GymBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Applications",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
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
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

            if (isLoading) {
                CircularProgressIndicator(color = GymGreen)
            } else if (errorMessage != null) {
                Text(text = errorMessage!!, color = GymRed)
            } else if (candidaturas.isEmpty()) {
                Text("Ainda não tens candidaturas.", color = Color.Gray)
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(candidaturas) { item ->
                        ApplicationCard(
                            candidatura = item,
                            onApprove = { updateStatus(item.id, "ACEITE") },
                            onReject = { updateStatus(item.id, "REJEITADA") }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ApplicationCard(
    candidatura: ApplicationResponse,
    onApprove: () -> Unit,
    onReject: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = GymCardColor),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = candidatura.offerTitle ?: "",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "By ${candidatura.candidateName ?: ""}",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Gray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (candidatura.candidateName ?: "?")
                            .take(1)
                            .uppercase(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "\"${candidatura.comment ?: ""}\"",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (candidatura.status == "PENDENTE") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = onApprove,
                        colors = ButtonDefaults.buttonColors(containerColor = GymGreen),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                    ) {
                        Text("Approve", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = onReject,
                        colors = ButtonDefaults.buttonColors(containerColor = GymRed),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                    ) {
                        Text("Reject", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            } else {
                val statusColor =
                    if (candidatura.status == "ACEITE") GymGreen else GymRed
                val statusText =
                    if (candidatura.status == "ACEITE") "Approved" else "Rejected"

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Box(
                        modifier = Modifier
                            .background(statusColor, RoundedCornerShape(50))
                            .padding(horizontal = 24.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = statusText,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
