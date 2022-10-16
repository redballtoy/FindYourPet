package com.example.redballtoy.findyourpet.common.domain.model.animal

import junit.framework.Assert.assertEquals
import org.junit.Test

class PhotoTests {
    private val mediumPhoto = "mediumPhoto"
    private val fullPhoto = "fullPhoto"
    private val invalidPhoto = "" //для тестирования в Photo.isValidPhoto()

    @Test
    fun photo_getSmallestAvailablePhoto_hasMediumPhoto() {
        //Given
        val photo = Media.Photo(mediumPhoto, fullPhoto)
        val expectedValue = mediumPhoto

        //When
        val smallestPhoto = photo.getSmallestAvailablePhoto()

        //Then
        assertEquals(smallestPhoto, expectedValue)
    }

    @Test
    fun photo_getSmallestAvailablePhoto_noPhotos(){
        //Given
        val photo = Media.Photo(invalidPhoto, invalidPhoto)
        val expectedValue = Media.Photo.NO_SIZE_AVAILABLE

        //When
        val smallestPhoto=photo.getSmallestAvailablePhoto()

        //Then
        assertEquals(smallestPhoto, expectedValue)
    }

}