package com.example.redballtoy.findyourpet.core.data.di.utils

import androidx.test.platform.app.InstrumentationRegistry
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

object JsonReader {
    fun getJson(path: String): String {
        return try {
            val context=InstrumentationRegistry.getInstrumentation().context
            val jsonStream:InputStream=context.assets.open(path)
            String(jsonStream.readBytes())
        }catch (exception:IOException){
            Timber.e(exception,"Error rearing network response ison asset")
            throw exception
        }
    }
}