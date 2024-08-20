package com.example.sum_v1

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class UsuarioViewModel : ViewModel() {
    var listaUsuarios = mutableStateListOf<Usuario>()

    fun agregarUsuario(usuario: Usuario) {
        listaUsuarios.add(usuario)
    }

    fun usuarioExiste(nombreUsuario: String, contraseña: String): Boolean {
        return listaUsuarios.any { it.nombreUsuario == nombreUsuario && it.contraseña == contraseña }
    }

    fun emailExiste(email: String): Boolean {
        return listaUsuarios.any { it.email == email }
    }
}
