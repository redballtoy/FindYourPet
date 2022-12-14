package com.example.redballtoy.findyourpet.core.data.api.model.mappers

import com.example.redballtoy.findyourpet.core.data.api.model.ApiEnvironment
import com.example.redballtoy.findyourpet.core.domain.model.animal.AnimalWithDetails
import com.example.redballtoy.findyourpet.core.domain.model.animal.AnimalWithDetails.*
import com.example.redballtoy.findyourpet.core.domain.model.animal.AnimalWithDetails.Details.*
import javax.inject.Inject

class ApiHabitatAdaptationMapper @Inject constructor() :
    ApiMapper<ApiEnvironment?, HabitatAdaptation> {
    override fun mapToDomain(apiEntity: ApiEnvironment?): HabitatAdaptation {
        return HabitatAdaptation(
            goodWithChildren = apiEntity?.children ?: true,
            goodWithDogs = apiEntity?.dogs ?: true,
            goodWithCats = apiEntity?.cats ?: true
        )
    }
}