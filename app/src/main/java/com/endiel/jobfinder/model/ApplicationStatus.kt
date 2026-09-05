package com.endiel.jobfinder.model

enum class ApplicationStatus {
    SUBMITTED,
    UNDER_REVIEW,
    ACCEPTED,
    REJECTED;

    fun displayName(): String {
        return when(this) {
            SUBMITTED -> "Submitted"
            UNDER_REVIEW -> "Under Review"
            ACCEPTED -> "Accepted"
            REJECTED -> "Rejected"
        }
    }
}