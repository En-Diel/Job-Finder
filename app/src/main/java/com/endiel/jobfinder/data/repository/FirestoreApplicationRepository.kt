package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.model.JobApplication
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreApplicationRepository @Inject constructor(
    /****
     * The first injection is for connection to Firestore and
     * the 2nd one is for getting the currently logged in firebase user's UID
     */
    private val firestore: FirebaseFirestore, private val authRepository: AuthRepository
) {
    suspend fun saveApplication(application: JobApplication) {

        val userId = authRepository.getCurrentUserId() ?: return
        val applicationData = mapOf(
            "jobId" to application.jobId,
            "fullName" to application.fullName,
            "email" to application.email,
            "phone" to application.phone,
            "coverLetter" to application.coverLetter,
            "status" to "SUBMITTED"
        )

        firestore
            .collection("users")
            .document(userId)
            .collection("applications")
            .document(application.id)
            .set(applicationData)
            .await()
    }

    suspend fun getApplications(): List<JobApplication> {

        val userId = authRepository.getCurrentUserId() ?: return emptyList()
        val snapshot = firestore
            .collection("users")
            .document(userId)
            .collection("applications")
            .get()
            .await()

        return snapshot.documents.mapNotNull { document ->
            val jobId = document.getString("jobId") ?: return@mapNotNull null
            val fullName = document.getString("fullName") ?: return@mapNotNull  null
            val email = document.getString("email") ?: return@mapNotNull  null
            val phone = document.getString("phone") ?: return@mapNotNull  null
            val coverLetter = document.getString("coverLetter") ?: return@mapNotNull  null

            JobApplication(
                id = document.id,
                jobId = jobId,
                fullName = fullName,
                email = email,
                phone = phone,
                coverLetter =coverLetter
            )
        }
    }
}