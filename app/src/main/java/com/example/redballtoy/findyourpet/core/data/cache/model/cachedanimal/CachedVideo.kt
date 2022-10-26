package com.example.redballtoy.findyourpet.core.data.cache.model.cachedanimal

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "videos",
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

data class CachedVideo(
    @PrimaryKey
) {

}
