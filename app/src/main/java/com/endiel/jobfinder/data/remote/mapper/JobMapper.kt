package com.endiel.jobfinder.data.remote.mapper

import com.endiel.jobfinder.data.remote.dto.JobDto
import com.endiel.jobfinder.model.Job

fun JobDto.toJob(): Job {

    val salary = when {
        salary_min != null && salary_max != null ->
            "$salary_min - $salary_max"
        salary_min != null ->
            "$salary_min"
        salary_max != null ->
            "$salary_max"
        else ->
            "Salary not specified"
    }

    return Job(
        id = id,
        companyName = company?.display_name ?: "Unknown company",
        jobTitle = title,
        location = location?.display_name ?: "Unknown location",
        salary = salary,
        category = category?.label ?: "Other",
        description = description,
        jobType = contract_type ?: "Unknown",
        jobPosition = contract_time ?: "Unknown",
        requirements = emptyList(),
        responsibilities = emptyList()
    )
}