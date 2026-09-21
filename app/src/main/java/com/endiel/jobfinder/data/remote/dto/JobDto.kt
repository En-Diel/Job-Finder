package com.endiel.jobfinder.data.remote.dto

data class JobDto(
    val id: String,
    val title: String,
    val description: String,
    val contract_time: String,
    val contract_type: String,
    val salary_min: Double?,
    val salary_max: Double?,
    val company: CompanyDto?,
    val location: LocationDto?,
    val category: CategoryDto?
)