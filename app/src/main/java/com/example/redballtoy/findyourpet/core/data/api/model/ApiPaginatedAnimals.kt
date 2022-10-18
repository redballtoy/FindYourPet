package com.example.redballtoy.findyourpet.core.data.api.model

import com.example.redballtoy.findyourpet.core.domain.model.animal.Animal
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiPaginatedAnimals(
    @field:Json(name = "animals") val animals: List<Animal>?,
    @field:Json(name = "pagination") val pagination: ApiPagination?
)

@JsonClass(generateAdapter = true)
data class ApiPagination(
    @field:Json(name = "count_per_page") val countPerPage: Int?,
    @field:Json(name = "total_count") val totalCount: Int?,
    @field:Json(name = "current_page") val currentPage: Int?,
    @field:Json(name = "total_pages") val totalPages: Int?
)
