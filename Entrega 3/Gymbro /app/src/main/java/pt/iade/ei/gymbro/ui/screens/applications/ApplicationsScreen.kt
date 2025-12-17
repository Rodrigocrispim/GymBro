package pt.iade.ei.gymbro.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import pt.iade.ei.gymbro.data.network.ApplicationResponse
import pt.iade.ei.gymbro.data.network.RetrofitInstance
import pt.iade.ei.gymbro.data.network.UpdateStatusRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Cores
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
        isLoading = true
        Log.d("DEBUG_APPS", "A tentar carregar candidaturas para a Oferta ID: $ofertaId")

        RetrofitInstance.api.getOfferApplications(ofertaId).enqueue(object : Callback<List<ApplicationResponse>> {
            override fun onResponse(call: Call<List<ApplicationResponse>>, response: Response<List<ApplicationResponse>>) {
                isLoading = false
                if (response.isSuccessful) {
                    val lista = response.body() ?: emptyList()
                    candidaturas = lista


                    Log.d("DEBUG_APPS", "Sucesso! Recebi ${lista.size} candidaturas.")
                    lista.forEach {
                        Log.d("DEBUG_APPS", " - Candidato: ${it.candidateName}, Status: ${it.status}, Comentário: ${it.comment}")
                    }

                } else {
                    errorMessage = "Erro Server: ${response.code()}"
                    Log.e("DEBUG_APPS", "Erro no Servidor: ${response.code()} - ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<ApplicationResponse>>, t: Throwable) {
                isLoading = false
                errorMessage = "Erro Rede: ${t.message}"
                Log.e("DEBUG_APPS", "Falha de Rede: ${t.message}")
            }
        })
    }


    fun updateStatus(id: Int, newStatus: String) {
        val request = UpdateStatusRequest(status = newStatus)
        Log.d("DEBUG_APPS", "A alterar status da App ID $id para $newStatus")

        RetrofitInstance.api.updateApplicationStatus(id, request).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Estado atualizado!", Toast.LENGTH_SHORT).show()
                    loadApplications()
                } else {
                    Toast.makeText(context, "Erro ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, "Falha de rede", Toast.LENGTH_SHORT).show()
            }
        })
    }

    LaunchedEffect(ofertaId) {
        loadApplications()
    }

    Scaffold(
        containerColor = Color(0xFF121212),
        topBar = {
            TopAppBar(
                title = { Text("Gerir Candidaturas (ID: $ofertaId)", color = Color.White, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF121212))
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {

            if (isLoading) {
                CircularProgressIndicator(color = GymGreen)
            } else if (errorMessage != null) {
                Text("Erro: $errorMessage", color = GymRed)
            } else if (candidaturas.isEmpty()) {
                // Se cair aqui, é porque o servidor devolveu lista vazia []
                Text("Lista Vazia (0 candidaturas)", color = Color.Gray)
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(candidaturas) { app ->
                        ApplicationCard(
                            app = app,
                            onApprove = { updateStatus(app.id, "ACEITE") },
                            onReject = { updateStatus(app.id, "REJEITADA") }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ApplicationCard(
    app: ApplicationResponse,
    onApprove: () -> Unit,
    onReject: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = GymCardColor),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(app.candidateName ?: "Sem Nome", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                val statusColor = when(app.status?.uppercase()) {
                    "ACEITE" -> GymGreen
                    "REJEITADA" -> GymRed
                    else -> Color(0xFFF1C40F)
                }
                Text(app.status ?: "PENDENTE", color = statusColor, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("\"${app.comment ?: ""}\"", color = Color.LightGray)
            Spacer(modifier = Modifier.height(16.dp))

            if (app.status == "PENDENTE") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = onApprove, colors = ButtonDefaults.buttonColors(containerColor = GymGreen), modifier = Modifier.weight(1f)) {
                        Text("Approve", color = Color.White)
                    }
                    Button(onClick = onReject, colors = ButtonDefaults.buttonColors(containerColor = GymRed), modifier = Modifier.weight(1f)) {
                        Text("Reject", color = Color.White)
                    }
                }
            }
        }
    }
}