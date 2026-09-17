package com.endiel.jobfinder.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class FirestoreProfileRepository @Inject constructor(private val firestore: FirebaseFirestore) {
    suspend fun saveProfile(
        userId: String,
        fullName: String,
        email: String,
        phone: String,
        location: String
    ) {
        val profile = mapOf(
            "fullName" to fullName,
            "email" to email,
            "phone" to phone,
            "location" to location
        )
        firestore.collection("users").document(userId).set(profile).await()
    }
}