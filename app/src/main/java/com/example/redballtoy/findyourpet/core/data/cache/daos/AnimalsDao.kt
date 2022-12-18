package com.example.redballtoy.findyourpet.core.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.redballtoy.findyourpet.core.data.cache.model.cachedanimal.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class AnimalsDao {

    @Transaction
    @Query("SELECT * FROM animals ORDER BY animalId DESC")
    abstract fun getAllAnimals(): Flowable<List<CachedAnimalAggregate>>

    @Transaction
    @Query("SELECT * FROM animals WHERE animalId IS :animalId")
    abstract fun getAnimal(
        animalId: Long
    ): Single<CachedAnimalAggregate>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAnimalAggregate(
        animal: CachedAnimalWithDetails,
        photos: List<CachedPhoto>,
        videos: List<CachedVideo>,
        tags: List<CachedTag>
    )

    fun insertAnimalsWithDetails(animalAggregates: List<CachedAnimalAggregate>) {
        for (animalAggregate in animalAggregates) {
            insertAnimalAggregate(
                animalAggregate.animal,
                animalAggregate.photos,
                animalAggregate.videos,
                animalAggregate.tags
            )
        }
    }

    @Query("SELECT DISTINCT type FROM animals")
    abstract suspend fun getAllTypes():List<String>




}