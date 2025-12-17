package pt.iade.ei.gymbro.ui.screens.applications

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gymbro.data.network.ApplicationResponse
import pt.iade.ei.gymbro.data.network.OfferResponse
import pt.iade.ei.gymbro.data.network.RetrofitInstance
import pt.iade.ei.gymbro.ui.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class CandidaturaItem(
    val application: ApplicationResponse,
    val offerTitle: String,
    val offerId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserApplicationsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToManage: (Int) -> Unit
) {
    var listaFinalCandidaturas by remember { mutableStateOf<List<CandidaturaItem>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }


    var hasOffers by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val myId = SessionManager.userId

    LaunchedEffect(Unit) {
        Log.e("DEBUG_GYMBRO", ">>> A INICIAR BUSCA DE CANDIDATURAS (User ID: $myId) <<<")


        RetrofitInstance.api.getOffers().enqueue(object : Callback<List<OfferResponse>> {
            override fun onResponse(call: Call<List<OfferResponse>>, response: Response<List<OfferResponse>>) {
                if (response.isSuccessful) {
                    val allOffers = response.body() ?: emptyList()


                    val myOffers = allOffers.filter { it.userId == myId }

                    Log.e("DEBUG_GYMBRO", "Total Ofertas: ${allOffers.size} | Minhas Ofertas: ${myOffers.size}")

                    if (myOffers.isEmpty()) {

                        hasOffers = false
                        isLoading = false
                        return
                    }


                    hasOffers = true
                    val tempLista = mutableListOf<CandidaturaItem>()
                    var requestsFaltam = myOffers.size


                    for (offer in myOffers) {
                        RetrofitInstance.api.getOfferApplications(offer.id).enqueue(object : Callback<List<ApplicationResponse>> {
                            override fun onResponse(callApp: Call<List<ApplicationResponse>>, responseApp: Response<List<ApplicationResponse>>) {
                                if (responseApp.isSuccessful) {
                                    val apps = responseApp.body() ?: emptyList()

                                    for (app in apps) {

                                        tempLista.add(CandidaturaItem(app, offer.title ?: "Treino", offer.id))
                                    }
                                }
                                requestsFaltam--
                                if (requestsFaltam == 0) {
                                    listaFinalCandidaturas = tempLista
                                    isLoading = false
                                }
                            }
                            override fun onFailure(callApp: Call<List<ApplicationResponse>>, t: Throwable) {
                                requestsFaltam--
                                if (requestsFaltam == 0) isLoading = false
                            }
                        })
                    }
                } else {
                    errorMessage = "Erro ao buscar ofertas: ${response.code()}"
                    isLoading = false
                }
            }
            override fun onFailure(call: Call<List<OfferResponse>>, t: Throwable) {
                errorMessage = "Erro de rede: ${t.message}"
                isLoading = false
            }
        })
    }

    Scaffold(
        containerColor = Color(0xFF121212),
        topBar = {
            TopAppBar(
                title = { Text("Candidaturas Recebidas", color = Color.White) },
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
                CircularProgressIndicator(color = Color(0xFF58B970))
            }
            else if (errorMessage != null) {
                Text(errorMessage!!, color = Color.Red)
            }
            else if (!hasOffers) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Não tens ofertas criadas.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Cria uma oferta primeiro para receberes candidaturas.", color = Color.Gray, fontSize = 14.sp)


                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Debug: O servidor diz que não tens ofertas com ID $myId", color = Color.Red, fontSize = 10.sp)
                }
            }
            else if (listaFinalCandidaturas.isEmpty()) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Ainda sem candidaturas.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("As tuas ofertas estão ativas, mas ninguém se inscreveu ainda.", color = Color.Gray, fontSize = 14.sp)
                }
            }
            else {

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(listaFinalCandidaturas) { item ->
                        CandidaturaDiretaCard(
                            item = item,
                            onClick = { onNavigateToManage(item.offerId) }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun CandidaturaDiretaCard(item: CandidaturaItem, onClick: () -> Unit) {
    val statusColor = when(item.application.status?.uppercase()) {
        "ACEITE" -> Color(0xFF58B970)
        "REJEITADA" -> Color(0xFFD32F2F)
        else -> Color(0xFFF1C40F)
    }
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C3E50)),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {

                Text(item.application.candidateName ?: "Utilizador Desconhecido", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(item.application.status ?: "PENDENTE", color = statusColor, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }

            Text("Candidatou-se a: ${item.offerTitle}", color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.background(Color(0xFF58B970), RoundedCornerShape(8.dp)).padding(8.dp)) {
                Text("Gerir Candidatura", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}