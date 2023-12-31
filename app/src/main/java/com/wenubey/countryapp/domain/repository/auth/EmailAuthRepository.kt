package com.wenubey.countryapp.domain.repository.auth

import com.google.firebase.auth.FirebaseUser
import com.wenubey.countryapp.utils.Resource

interface EmailAuthRepository {

    val currentUser: FirebaseUser?

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Resource<Boolean>

    suspend fun sendEmailVerification(): Resource<Boolean>

    suspend fun signInWithEmailAndPassword(email: String, password: String): Resource<Boolean>

    suspend fun sendPasswordResetEmail(email: String): Resource<Boolean>

    fun getAuthState(): Boolean


}
