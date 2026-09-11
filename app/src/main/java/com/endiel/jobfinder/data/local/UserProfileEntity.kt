package com.endiel.jobfinder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(

    @PrimaryKey
    val id: Int = 1,

    val fullName: String,
    val email: String,
    val phone: String,
    val location: String
)