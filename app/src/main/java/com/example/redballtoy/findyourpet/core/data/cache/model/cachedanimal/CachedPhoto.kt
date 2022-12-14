package com.example.redballtoy.findyourpet.core.data.cache.model.cachedanimal

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.redballtoy.findyourpet.core.domain.model.animal.Media


@Entity(
    tableName = "photos",
    foreignKeys = [
        ForeignKey(
            entity = CachedAnimalWithDetails::class,
            parentColumns = ["animalId"],
            childColumns = ["animalId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("animalId")]
)

data class CachedPhoto(
    @PrimaryKey(autoGenerate = true)
    val photoId: Long = 0,
    val animateId: Long,
    val medium: String,
    val full: String
) {
    companion object {
        fun fromDomain(animateId: Long, photo: Media.Photo): CachedPhoto {
            val (medium, full) = photo
            return CachedPhoto(animateId,animateId, medium, full)
        }
    }

    fun toDomain(): Media.Photo=Media.Photo(medium,full)

}
