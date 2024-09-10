package com.example.sum_v1

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf

data class Categoria(val nombre: String)


object CategoriaManager {
    val listaCategorias = mutableStateListOf<Categoria>()
    val ingredientesPorCategoria = mutableStateMapOf<Categoria, MutableList<Ingrediente>>()

    fun agregarCategoria(nombre: String) {
        val nuevaCategoria = Categoria(nombre)
        listaCategorias.add(nuevaCategoria)
        ingredientesPorCategoria[nuevaCategoria] = mutableListOf()
    }

    fun agregarIngrediente(ingrediente: Ingrediente) {
        ingredientesPorCategoria[ingrediente.categoria]?.add(ingrediente)
    }

    fun obtenerIngredientes(categoria: Categoria): List<Ingrediente> {
        return ingredientesPorCategoria[categoria] ?: listOf()
    }

    fun eliminarCategoria(nombre: String) {
        listaCategorias.removeIf { it.nombre == nombre }
    }

    fun obtenerCategorias(): List<Categoria> {
        return listaCategorias
    }
}

