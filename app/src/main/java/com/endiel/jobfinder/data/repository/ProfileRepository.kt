package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.data.local.UserProfileEntity
import com.endiel.jobfinder.data.local.dao.ProfileDao
import com.endiel.jobfinder.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileDao: ProfileDao,
    private val authRepository: AuthRepository,
    private val firestoreProfileRepository: FirestoreProfileRepository
) {

    fun getProfile(): Flow<UserProfile?> {
        // ?: Means if the value on the left is not null USE IT otherwise do the thing on the right
        val userId = authRepository.getCurrentUserId() ?: return flowOf(null)

        return profileDao.getProfile(userId)
            .map { entity ->

                entity?.let {
                    UserProfile(
                        fullName = it.fullName,
                        email = it.email,
                        phone = it.phone,
                        location = it.location
                    )
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