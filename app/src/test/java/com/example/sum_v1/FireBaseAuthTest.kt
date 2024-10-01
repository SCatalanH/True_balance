package com.example.sum_v1

import com.google.firebase.auth.FirebaseAuth
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Mock
import org.junit.Before



class FirebaseAuthTest {

    @Mock
    private lateinit var mockFirebaseAuth: FirebaseAuth

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testRegistroUsuario_exitoso() {
        val email = "test@test.com"
        val password = "123456"
        mockFirebaseAuth.createUserWithEmailAndPassword(email, password)
        Mockito.verify(mockFirebaseAuth).createUserWithEmailAndPassword(email, password)
    }

    @Test
    fun testLoginUsuario_fallido() {
        val email = "test@fail.com"
        val password = "wrongpassword"
        mockFirebaseAuth.signInWithEmailAndPassword(email, password)
        Mockito.verify(mockFirebaseAuth).signInWithEmailAndPassword(email, password)
    }
}
