package com.endiel.jobfinder.data.repository

import androidx.room.Dao
import com.endiel.jobfinder.data.local.UserProfileEntity
import com.endiel.jobfinder.data.local.dao.ProfileDao
import com.endiel.jobfinder.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileDao: ProfileDao,
    private val authRepository: AuthRepository,
    private val firestoreProfileRepository: FirestoreProfileRepository
) {

    fun getProfile(): Flow<UserProfile?> = flow {

        val userId = authRepository.getCurrentUserId()

        if (userId == null) {
            emit(null)
            return@flow
        }

        val localProfile = profileDao.getProfile(userId)
        // Fetch from local room database
        localProfile.collect { entity ->
            if (entity !== null) {
                emit(
                    UserProfile(
                        fullName = entity.fullName,
                        email = entity.email,
                        phone = entity.phone,
                        location = entity.location
                    )
                )
            } else {
                // Fetch from remote firebase firestore
                val firestoreProfile = firestoreProfileRepository.getProfile(userId)

                if (firestoreProfile !== null) {

                    val profile = UserProfile(
                        fullName = firestoreProfile["fullName"] as? String ?: "",
                        email = firestoreProfile["email"] as? String ?: "",
                        phone = firestoreProfile["phone"] as? String ?: "",
                        location = firestoreProfile["location"] as? String ?: ""
                    )

                    profileDao.saveProfile(
                        UserProfileEntity(
                            userId = userId,
                            fullName = profile.fullName,
                            email = profile.email,
                            phone = profile.phone,
                            location = profile.location
                        )
                    )
                    emit(profile)
                } else {
                    emit(null)
                }
            }
        }
    }

    suspend fun saveProfile(profile: UserProfile) {
        val userId = authRepository.getCurrentUserId() ?: return

        val entity = UserProfileEntity(
            userId = userId,
            fullName = profile.fullName,
            email = profile.email,
            phone = profile.phone,
            location = profile.location
        )

        profileDao.saveProfile(entity)

        firestoreProfileRepository.saveProfile(
            userId = userId,
            fullName = profile.fullName,
            email = profile.email,
            phone = profile.phone,
            location = profile.location
        )
    }
}