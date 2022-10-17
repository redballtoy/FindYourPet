package com.example.redballtoy.findyourpet.common.data.api.model.mappers

import com.example.redballtoy.findyourpet.common.data.api.model.ApiVideoLink
import com.example.redballtoy.findyourpet.common.domain.model.animal.Media
import javax.inject.Inject

class ApiVideoMapped @Inject constructor() : ApiMapper<ApiVideoLink?, Media.Video> {
    override fun mapToDomain(apiEntity: ApiVideoLink?): Media.Video {
        return Media.Video(
            video = apiEntity?.embed.orEmpty()
        )
    }
}