package com.example.redballtoy.findyourpet.core.data.preferences

interface Preferenses {
    fun putToken(token: String)

    fun putTokenExpirationTime(time: Long)

    fun putTokenType(tokenType: String)

    fun getToken():String

    fun getTokenExoirationTime(): Long

    fun getTokenType(): String

    fun deleteTokenInfo()
}