package com.example.sum_v1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

class CrearPublicacionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrearPublicacionScreen(onBackPressed = { finish() })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearPublicacionScreen(onBackPressed: () -> Unit) {
    val context = LocalContext.current
    var titulo by remember { mutableStateOf("") }
    var cuerpo by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Crear Publicación", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Nueva Publicación", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(16.dp))


                TextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))


                TextField(
                    value = cuerpo,
                    onValueChange = { cuerpo = it },
                    label = { Text("Cuerpo") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Spacer(modifier = Modifier.height(16.dp))


                Button(
                    onClick = {
                        if (titulo.isNotEmpty() && cuerpo.isNotEmpty()) {
                            PublicacionManager.agregarPublicacion(titulo, cuerpo)
                            Toast.makeText(context, "Publicación guardada", Toast.LENGTH_SHORT).show()
                            onBackPressed()
                        } else {
                            Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Guardar Publicación")
                }
            }
        }
    )
}
