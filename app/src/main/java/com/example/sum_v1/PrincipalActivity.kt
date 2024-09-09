package com.example.sum_v1

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

class PrincipalActivity : ComponentActivity() {
    private val usuariosVistaModel = ViewModelSingleton.usuariosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombreUsuario = intent.getStringExtra("nombreUsuario") ?: "Usuario"

        setContent {
            PrincipalScreen(
                nombreUsuario = nombreUsuario,
                listaUsuarios = usuariosVistaModel.listaUsuarios.map { it.nombreUsuario },
                listaCategorias = CategoriaManager.listaCategorias // Obtenemos las categorías
            )
        }
    }
}

@Composable
fun PrincipalScreen(nombreUsuario: String, listaUsuarios: List<String>, listaCategorias: List<Categoria>) {
    val context = LocalContext.current  // Obtener el contexto en Compose

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo_sumativa_2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Bienvenido, $nombreUsuario!",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Estamos contentos de tenerte aquí.",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón para gestionar categorías
            Button(
                onClick = {
                    val intent = Intent(context, CategoriaActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Gestionar Categorías")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Mostrar los usuarios registrados
            Text(
                text = "Usuarios Registrados:",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(listaUsuarios) { usuario ->
                    Text(
                        text = usuario,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Mostrar las categorías como botones
            Text(
                text = "Categorías Disponibles:",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(listaCategorias) { categoria ->
                    Button(
                        onClick = {
                            // Aquí podrías añadir lógica para navegar a la vista de la categoría
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = categoria.nombre)
                    }
                }
            }
        }
    }
}
