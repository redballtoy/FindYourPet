package com.example.redballtoy.findyourpet.core.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    fun parse(dateTimeString: String):LocalDateTime=try {
        LocalDateTime.parse(dateTimeString)
    }catch (e:Exception){
        val dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
        LocalDateTime.parse(dateTimeString,dataFormatter)
    }
}