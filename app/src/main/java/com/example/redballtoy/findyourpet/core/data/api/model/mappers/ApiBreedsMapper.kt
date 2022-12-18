package com.example.redballtoy.findyourpet.core.data.api.model.mappers

import com.example.redballtoy.findyourpet.core.data.api.model.ApiBreeds
import com.example.redballtoy.findyourpet.core.domain.model.animal.AnimalWithDetails
import com.example.redballtoy.findyourpet.core.domain.model.animal.AnimalWithDetails.*
import com.example.redballtoy.findyourpet.core.domain.model.animal.AnimalWithDetails.Details.*
import javax.inject.Inject

class ApiBreedsMapper @Inject constructor() : ApiMapper<ApiBreeds?, Breed> {
    override fun mapToDomain(apiEntity: ApiBreeds?): Breed {
        return Breed(
            primary = apiEntity?.primary.orEmpty(),
            secondary = apiEntity?.secondary.orEmpty()
        )
    }
}