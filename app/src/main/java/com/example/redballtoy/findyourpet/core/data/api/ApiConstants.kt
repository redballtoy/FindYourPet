package com.example.redballtoy.findyourpet.core.data.api

import com.example.redballtoy.findyourpet.common.data.api.model.GO

object ApiConstants {
    const val BASE_ENDPOINT = "https://api.petfinder.com/v2/"
    const val AUTH_ENDPOINT = "oauth/token/"
    const val ANIMALS_ENDPOINT = "animals"

    const val KEY = "v5FVO2bLCULV26GXAr3nx58FyelnUJMEEK6esufNQ1Zs92ULyI"
    const val SECRET = "66JqjSkJ4cNko9BWRHVjil0pNYAjHxDpuUuB13VR"
    fun aK(): String {
        return SECRET.substring(35).reversed() + GO.f1 + "5687" + GO.f2 + "3657" +
                GO.f2 + SECRET.substring(7, 10) + GO.f1 + " 4fj6" + GO.f3 +
                SECRET.substring(1..4) + GO.f3
    }
}

object ApiParameters{
    const val TOKEN_TYPE = "Bearer "
    const val AUTH_HEADER = "Authorization"
    const val GRANT_TYPE_KEY = "grant_type"
    const val GRANT_TYPE_VALUE="client_credentials"
    const val CLIENT_ID = "client_id"
    const val CLIENT_SECRET = "client_secret"

    const val PAGE = "page"
    const val LIMIT = "limit"
    const val LOCATION = "location"
    const val DISTANCE = "distance"
    const val NAME = "name"
    const val AGE = "age"
    const val TYPE = "type"
}