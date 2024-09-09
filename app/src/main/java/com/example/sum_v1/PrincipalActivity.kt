package com.example.sum_v1

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import com.example.sum_v1.ui.theme.Purple40

class PrincipalActivity : ComponentActivity() {
    private val usuariosVistaModel = ViewModelSingleton.usuariosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombreUsuario = intent.getStringExtra("nombreUsuario") ?: "Usuario"

        setContent {
            PrincipalScreen(
                nombreUsuario = nombreUsuario,
                listaUsuarios = usuariosVistaModel.listaUsuarios.map { it.nombreUsuario },
                listaCategorias = CategoriaManager.listaCategorias
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(nombreUsuario: String, listaUsuarios: List<String>, listaCategorias: List<Categoria>) {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = "Categorías",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(listaCategorias) { categoria ->
                        Button(
                            onClick = {

                            },
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = categoria.nombre, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        },
        content = {

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "True Balance", color = Color.White) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Abrir Drawer", tint = Color.White)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Purple40,
                            titleContentColor = Color.White,
                            navigationIconContentColor = Color.White
                        )
                    )
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
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
                    }
                }
            }
        }
    )
}
