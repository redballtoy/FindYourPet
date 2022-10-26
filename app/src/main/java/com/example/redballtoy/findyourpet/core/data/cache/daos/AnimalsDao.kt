package com.example.redballtoy.findyourpet.core.data.cache.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Flowable

@Dao
class AnimalsDao {

    @Transaction
    @Query("SELECT * FROM animals ORDER BY a DESC")
    abstract fun getAllAnimals():Flowable<List<CachedAnimalAggregate>>
}