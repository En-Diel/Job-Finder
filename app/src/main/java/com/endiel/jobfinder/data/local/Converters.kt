package com.endiel.jobfinder.data.local

import androidx.room.TypeConverter
import com.endiel.jobfinder.model.ApplicationStatus

// This will convert the list to string and string to list, to be stored easily in DB
class Converters {

    @TypeConverter
    fun fromList(value: List<String>): String {
        return value.joinToString(("|||"))
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        return value.split("|||")
    }

    @TypeConverter
    fun fromApplicationStatus(status: ApplicationStatus): String {
        return status.name
    }

    @TypeConverter
    fun toApplicationStatus(value: String): ApplicationStatus {
        return ApplicationStatus.valueOf(value)
    }
}