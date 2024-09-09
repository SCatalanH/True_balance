package com.example.sum_v1

import androidx.compose.runtime.mutableStateListOf

data class Categoria(val nombre: String)


object CategoriaManager {
    val listaCategorias = mutableStateListOf<Categoria>()

    fun agregarCategoria(nombre: String) {
        listaCategorias.add(Categoria(nombre))
    }

    fun eliminarCategoria(nombre: String) {
        listaCategorias.removeIf { it.nombre == nombre }
    }

    fun obtenerCategorias(): List<Categoria> {
        return listaCategorias
    }
}

