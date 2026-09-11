package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.data.local.UserProfileEntity
import com.endiel.jobfinder.data.local.dao.ProfileDao
import com.endiel.jobfinder.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileDao: ProfileDao
) {

    fun getProfile(): Flow<UserProfile?> {

        return profileDao.getProfile()
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

        val entity = UserProfileEntity(
            id = 1,
            fullName = profile.fullName,
            email = profile.email,
            phone = profile.phone,
            location = profile.location
        )

        profileDao.saveProfile(entity)
    }
}