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
import androidx.compose.ui.graphics.Color

class AgregarIngredienteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgregarIngredienteScreen(onBackPressed = { finish() })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarIngredienteScreen(onBackPressed: () -> Unit) {
    val context = LocalContext.current
    var nombreIngrediente by remember { mutableStateOf("") }
    var caloriasIngrediente by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var categoriaSeleccionada by remember { mutableStateOf<Categoria?>(null) }
    val listaCategorias = CategoriaManager.obtenerCategorias()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Agregar Ingrediente", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6200EE))
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
                Text(text = "Agregar Nuevo Ingrediente", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(16.dp))


                TextField(
                    value = nombreIngrediente,
                    onValueChange = { nombreIngrediente = it },
                    label = { Text("Nombre del Ingrediente") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))


                TextField(
                    value = caloriasIngrediente,
                    onValueChange = { caloriasIngrediente = it },
                    label = { Text("Calorías del Ingrediente") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(16.dp))


                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = categoriaSeleccionada?.nombre ?: "Selecciona una categoría",
                        onValueChange = {},
                        label = { Text("Categoría") },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White
                        ),
                        singleLine = true
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        if (listaCategorias.isEmpty()) {
                            DropdownMenuItem(
                                text = { Text("No hay categorías") },
                                onClick = { expanded = false }
                            )
                        } else {
                            listaCategorias.forEach { categoria ->
                                DropdownMenuItem(
                                    text = { Text(categoria.nombre) },
                                    onClick = {
                                        categoriaSeleccionada = categoria
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                Button(
                    onClick = {
                        if (nombreIngrediente.isNotEmpty() && caloriasIngrediente.isNotEmpty() && categoriaSeleccionada != null) {
                            val calorias = caloriasIngrediente.toIntOrNull()
                            if (calorias != null) {
                                CategoriaManager.agregarIngrediente(
                                    Ingrediente(nombreIngrediente, calorias, categoriaSeleccionada!!)
                                )
                                Toast.makeText(context, "Ingrediente creado exitosamente", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Por favor, ingrese un valor numérico para las calorías", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Guardar Ingrediente")
                }
            }
        }
    )
}
