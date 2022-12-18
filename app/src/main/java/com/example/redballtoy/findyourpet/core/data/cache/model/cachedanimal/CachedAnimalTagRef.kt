package com.example.redballtoy.findyourpet.core.data.cache.model.cachedanimal

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["animalId", "tag"], indices = [Index("tag")])
data class CachedAnimalTagRef(
    val animalId: Long,
    val tag: String
)
