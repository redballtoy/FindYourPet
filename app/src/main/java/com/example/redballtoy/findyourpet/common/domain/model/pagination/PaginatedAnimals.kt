package com.example.redballtoy.findyourpet.common.domain.model.pagination

import com.example.redballtoy.findyourpet.common.domain.model.animal.details.AnimalWithDetails

//This value object associates a list of animals with a specific page
data class PaginatedAnimals(
    val animals: List<AnimalWithDetails>,
    val pagination: Pagination
)