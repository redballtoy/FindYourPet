package com.example.redballtoy.findyourpet.core.data.api.model.mappers

import com.example.redballtoy.findyourpet.core.data.api.model.ApiAnimal
import com.example.redballtoy.findyourpet.core.domain.model.animal.AdoptionStatus
import com.example.redballtoy.findyourpet.core.domain.model.animal.AnimalWithDetails
import com.example.redballtoy.findyourpet.core.domain.model.animal.AnimalWithDetails.*
import com.example.redballtoy.findyourpet.core.domain.model.animal.Media
import com.example.redballtoy.findyourpet.core.domain.model.organization.Organization
import com.example.redballtoy.findyourpet.core.utils.DateTimeUtils
import java.util.*
import javax.inject.Inject

class ApiAnimalMapper @Inject constructor(
    private val apiBreedsMapper: ApiBreedsMapper,
    private val apiColorsMapper: ApiColorsMapper,
    private val apiHealthDetailsMapped: ApiHealthDetailsMapped,
    private val apiHabitatAdaptationMapper: ApiHabitatAdaptationMapper,
    private val apiPhotoMapper: ApiPhotoMapper,
    private val apiVideoMapper: ApiVideoMapper,
    private val apiContactMapper: ApiContactMapper
) : ApiMapper<ApiAnimal, AnimalWithDetails> {
    override fun mapToDomain(apiEntity: ApiAnimal): AnimalWithDetails {
        return AnimalWithDetails(
            id = apiEntity.id ?: throw MappingException("Animal Id cannot be null"),
            name = apiEntity.name.orEmpty(),
            type = apiEntity.type.orEmpty(),
            details = parseAnimalDetails(apiEntity),
            media = mapMedia(apiEntity),
            tags = apiEntity.tags.orEmpty().map { it.orEmpty() },
            adoptionStatus = parseAdoptionStatus(apiEntity.status),
            publishedAt = DateTimeUtils.parse(apiEntity.publishedAt.orEmpty())//throws exception if empty


        )
    }

    private fun parseAdoptionStatus(status: String?): AdoptionStatus {
        if (status.isNullOrEmpty()) return AdoptionStatus.UNKNOWN
        return AdoptionStatus.valueOf(status.uppercase(Locale.ROOT))
    }

    private fun mapMedia(apiAnimal: ApiAnimal): Media {
        return Media(
            photos = apiAnimal.photos?.map { apiPhotoMapper.mapToDomain(it) }.orEmpty(),
            videos = apiAnimal.videos?.map { apiVideoMapper.mapToDomain(it)}.orEmpty()
        )
    }

    private fun parseAnimalDetails(apiAnimal: ApiAnimal): Details {
        return Details(
            description = apiAnimal.description.orEmpty(),
            age = parseAge(apiAnimal.age),
            species = apiAnimal.species.orEmpty(),
            breed = apiBreedsMapper.mapToDomain(apiAnimal.breeds),
            colors = apiColorsMapper.mapToDomain(apiAnimal.colors),
            gender = parseGender(apiAnimal.gender),
            size = parseSizeAnimal(apiAnimal.size),
            coat = parseCoatAnimal(apiAnimal.coat),
            healthDetails = apiHealthDetailsMapped.mapToDomain(apiAnimal.attributes),
            habitatAdaptation = apiHabitatAdaptationMapper.mapToDomain(apiAnimal.environment),
            organization = mapOrganization(apiAnimal)


        )
    }

    private fun mapOrganization(apiAnimal: ApiAnimal): Organization {
        return Organization(
            id = apiAnimal.organizationId
                ?: throw MappingException("Organization Id cannot be null"),
            contact = apiContactMapper.mapToDomain(apiAnimal.contact),
            distance = apiAnimal.distance ?: -1f
        )
    }

    private fun parseCoatAnimal(coat: String?): Details.Coat {
        if (coat.isNullOrEmpty()) return Details.Coat.UNKNOWN
        return Details.Coat.valueOf(coat.uppercase(Locale.ROOT))
    }

    private fun parseSizeAnimal(size: String?): Details.Size {
        if (size.isNullOrEmpty()) return Details.Size.UNKNOWN
        return Details.Size.valueOf(size.replace(' ', '_').uppercase(Locale.ROOT))
    }

    private fun parseGender(gender: String?): Details.Gender {
        if (gender.isNullOrEmpty()) return Details.Gender.UNKNOWN
        return Details.Gender.valueOf(gender.uppercase(Locale.ROOT))

    }

    private fun parseAge(age: String?): Details.Age {
        if (age.isNullOrEmpty()) return Details.Age.UNKNOWN

        //will throw IllegalStateException if the string does not match any enum value
        return Details.Age.valueOf(age.uppercase(Locale.ROOT))
    }
}