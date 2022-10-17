package com.example.redballtoy.findyourpet.common.data.api.model.mappers


import com.example.redballtoy.findyourpet.common.data.api.model.ApiAttributes
import com.example.redballtoy.findyourpet.common.domain.model.animal.details.HealthDetails
import javax.inject.Inject

class ApiHealthDetailsMapped @Inject constructor() : ApiMapper<ApiAttributes?, HealthDetails> {
    override fun mapToDomain(apiEntity: ApiAttributes?): HealthDetails {
        return HealthDetails(
            isSpayedOrNeutered = apiEntity?.spayedNeutered ?: false,
            isDeclawed = apiEntity?.declawed ?: false,
            hasSpecialNeeds = apiEntity?.specialNeeds ?: false,
            shotsAreCurrent = apiEntity?.schotsCurrent ?: false
        )
    }
}