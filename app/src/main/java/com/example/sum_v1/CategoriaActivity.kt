package com.example.sum_v1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete


class CategoriaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CategoriaScreen()
        }
    }
}

@Composable
fun CategoriaScreen() {
    var nuevaCategoria by remember { mutableStateOf(TextFieldValue("")) }
    val listaCategorias = CategoriaManager.obtenerCategorias()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Gestión de Categorías", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = nuevaCategoria,
            onValueChange = { nuevaCategoria = it },
            label = { Text("Nueva Categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                if (nuevaCategoria.text.isNotEmpty()) {
                    CategoriaManager.agregarCategoria(nuevaCategoria.text)
                    nuevaCategoria = TextFieldValue("")  // Limpiar el campo de texto
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Categoría")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(text = "Categorías Existentes:", style = MaterialTheme.typography.titleMedium)

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listaCategorias) { categoria ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = categoria.nombre, style = MaterialTheme.typography.bodyLarge)


                    IconButton(onClick = {
                        CategoriaManager.eliminarCategoria(categoria.nombre)
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar Categoría")
                    }
                }
            }
        }
    }
}
