package com.example.sum_v1

import androidx.compose.runtime.mutableStateListOf

data class Publicacion(
    val titulo: String,
    val cuerpo: String
)

object PublicacionManager {

    val listaPublicaciones = mutableStateListOf<Publicacion>()


    fun agregarPublicacion(titulo: String, cuerpo: String) {
        val nuevaPublicacion = Publicacion(titulo, cuerpo)
        listaPublicaciones.add(nuevaPublicacion)
    }


    fun obtenerPublicaciones(): List<Publicacion> {
        return listaPublicaciones
    }
}
