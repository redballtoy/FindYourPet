package com.example.redballtoy.findyourpet.core.data.api.model.mappers

import com.example.redballtoy.findyourpet.core.data.api.model.ApiVideoLink
import com.example.redballtoy.findyourpet.core.domain.model.animal.Media
import javax.inject.Inject

class ApiVideoMapper @Inject constructor() : ApiMapper<ApiVideoLink?, Media.Video> {
    override fun mapToDomain(apiEntity: ApiVideoLink?): Media.Video {
        return Media.Video(
            video = apiEntity?.embed.orEmpty()
        )
    }
}