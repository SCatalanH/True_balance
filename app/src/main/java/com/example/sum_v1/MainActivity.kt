package com.example.sum_v1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    private val usuariosVistaModel = ViewModelSingleton.usuariosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(usuariosVistaModel) { nombreUsuario ->
                val intent = Intent(this, PrincipalActivity::class.java)
                intent.putExtra("nombreUsuario", nombreUsuario)
                startActivity(intent)
            }
        }
    }
}

@Composable
fun LoginScreen(viewModel: UsuarioViewModel, onLoginSuccess: (String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    val context = LocalContext.current

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de Usuario") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    println("Usuarios al intentar login: ${viewModel.listaUsuarios.size}")
                    println("Usuarios registrados: ${viewModel.listaUsuarios}")
                    if (viewModel.usuarioExiste(username, password)) {
                        onLoginSuccess(username)
                    } else {
                        mensajeError = "Nombre de usuario o contraseña incorrectos"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (mensajeError.isNotEmpty()) {
                Text(mensajeError, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))


            TextButton(
                onClick = {
                    val intent = Intent(context, RecuperarPassActivity::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text("¿Olvidaste tu contraseña?", color = Color.White)
            }
        }
    }
}