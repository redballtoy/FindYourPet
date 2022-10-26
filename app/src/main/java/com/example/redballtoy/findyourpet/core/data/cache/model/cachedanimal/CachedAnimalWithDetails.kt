package com.example.redballtoy.findyourpet.core.data.cache.model.cachedanimal

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.redballtoy.findyourpet.core.data.cache.model.cachedorganization.CachedOrganization
import com.example.redballtoy.findyourpet.core.domain.model.animal.AdoptionStatus
import com.example.redballtoy.findyourpet.core.domain.model.animal.Media
import com.example.redballtoy.findyourpet.core.domain.model.animal.details.AnimalWithDetails
import com.example.redballtoy.findyourpet.core.utils.DateTimeUtils

@Entity(
    tableName = "animals",
    foreignKeys = [
        ForeignKey(
            entity = CachedOrganization::class,
            parentColumns = ["organizationId"],
            childColumns = ["organizationId"],
            onDelete = CASCADE
        )
    ],
    indices = [Index("organizationId")]
)
data class CachedAnimalWithDetails(
    @PrimaryKey(autoGenerate = false)
    val animalId: Long,
    val orgavizationId: String,
    val name: String,
    val type: String,
    val description: String,
    val age: String,
    val species: String,
    val primaryBreed: String,
    val secondaryBreed: String,
    val primaryColor: String,
    val secondaryColor: String,
    val tertiaryColor: String,
    val gender: String,
    val size: String,
    val coat: String,
    val isSpayedOrNeutered: Boolean,
    val isDeclawed: Boolean,
    val hasSpecialNeeds: Boolean,
    val shotsAreCurrent: Boolean,
    val goodWithChildren: Boolean,
    val goodWithDogs: Boolean,
    val goodWithCats: Boolean,
    val adoptionStatus: String,
    val publishedAt: String
) {
    companion object {
        fun fromDomain(domainModel: AnimalWithDetails): CachedAnimalWithDetails {
            val details = domainModel.details
            val healthDetails = details.healthDetails
            val habitatAdaptation = details.habitatAdaptation

            return CachedAnimalWithDetails(
                animalId = domainModel.id,
                orgavizationId = details.organization.id,
                name = domainModel.name,
                type = domainModel.type,
                description = details.description,
                age = details.age.toString(),
                species = details.species,
                primaryBreed = details.breed.primary,
                secondaryBreed = details.breed.secondary,
                primaryColor = details.colors.primary,
                secondaryColor = details.colors.secondary,
                tertiaryColor = details.colors.tertiary,
                gender = details.gender.toString(),
                size = details.size.toString(),
                coat = details.coat.toString(),
                isSpayedOrNeutered = healthDetails.isSpayedOrNeutered,
                isDeclawed = healthDetails.isDeclawed,
                hasSpecialNeeds = healthDetails.hasSpecialNeeds,
                shotsAreCurrent = healthDetails.shotsAreCurrent,
                goodWithChildren = habitatAdaptation.goodWithChildren,
                goodWithDogs = habitatAdaptation.goodWithDogs,
                goodWithCats = habitatAdaptation.goodWithCats,
                adoptionStatus = domainModel.adoptionStatus.toString(),
                publishedAt = domainModel.publishedAt.toString()

            )
        }
    }
    fun toDomain(
        photos:List<CachedPhoto>,
        videos:List<CachedVideo>,
        tags: List<CachedTag>,
        organization: CachedOrganization
    ):AnimalWithDetails{
        return AnimalWithDetails(
            id = animalId,
            name=name,
            type=type,
            details = mapDetails(organization),
            media = Media(
                photos=photos.map { it.toDomain() },
                videos=videos.map { it.toDomain() }
            ),
            tags = tags.map { it.tag },
            adoptionStatus = AdoptionStatus.valueOf(adoptionStatus),
            publishedAt=DateTimeUtils.parse(publishedAt)
        )
    }

}