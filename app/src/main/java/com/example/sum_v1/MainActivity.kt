package com.example.sum_v1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(
                onLoginSuccess = { nombreUsuario ->
                    val intent = Intent(this, PrincipalActivity::class.java)
                    intent.putExtra("nombreUsuario", nombreUsuario)
                    startActivity(intent)
                    finish()
                },
                onForgotPasswordClick = {
                    val intent = Intent(this, RecuperarPassActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit, onForgotPasswordClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de texto para el email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para la contraseña
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de login
        Button(
            onClick = {
                autenticarUsuario(email, password, context, onLoginSuccess)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón de "Olvidaste tu contraseña?"
        TextButton(
            onClick = { onForgotPasswordClick() }
        ) {
            Text("¿Olvidaste tu contraseña?")
        }
    }
}

// Función para autenticar al usuario con Firebase
fun autenticarUsuario(email: String, password: String, context: android.content.Context, onLoginSuccess: (String) -> Unit) {
    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Inicio de sesión exitoso
                val usuario = FirebaseAuth.getInstance().currentUser
                val nombreUsuario = usuario?.email ?: "Usuario"
                onLoginSuccess(nombreUsuario)
            } else {
                // Error en el inicio de sesión
                Toast.makeText(context, "Error en el inicio de sesión: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
}
