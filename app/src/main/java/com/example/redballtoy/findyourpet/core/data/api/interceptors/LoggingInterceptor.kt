package com.example.redballtoy.findyourpet.core.data.api.interceptors

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Inject

class LoggingInterceptor @Inject constructor():HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Timber.i(message)
    }
}