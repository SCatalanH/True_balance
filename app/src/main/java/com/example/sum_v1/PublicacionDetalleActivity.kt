package com.example.sum_v1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class PublicacionDetalleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val titulo = intent.getStringExtra("titulo") ?: "Sin título"
        val cuerpo = intent.getStringExtra("cuerpo") ?: "Sin contenido"

        setContent {
            PublicacionDetalleScreen(titulo, cuerpo)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublicacionDetalleScreen(titulo: String, cuerpo: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = titulo, fontSize = 20.sp) }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Título: $titulo", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Contenido:", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = cuerpo, style = MaterialTheme.typography.bodyLarge)
            }
        }
    )
}
