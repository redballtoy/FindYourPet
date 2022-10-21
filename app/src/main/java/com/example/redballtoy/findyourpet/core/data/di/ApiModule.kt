package com.example.redballtoy.findyourpet.core.data.di

import com.appmattus.certificatetransparency.certificateTransparencyInterceptor
import com.example.redballtoy.findyourpet.core.data.api.ApiConstants
import com.example.redballtoy.findyourpet.core.data.api.PetFinderApi
import com.example.redballtoy.findyourpet.core.data.api.interceptors.AuthenticationInterceptor
import com.example.redballtoy.findyourpet.core.data.api.interceptors.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): PetFinderApi {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory((MoshiConverterFactory.create()))
            .build()
            .create()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): PetFinderApi {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PetFinderApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authentionInterceptor: AuthenticationInterceptor
    ): OkHttpClient {
        val hostname = "**.petfinder.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/U8zLlKBQLcRpbcte+Y0kpfoe0pMz+ABQqhAdPlPtf7M=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .build()
        val ctInterceptor = certificateTransparencyInterceptor {
            +"*.petfinder.com"
            +"petfinder.com"
        }
        return OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .addNetworkInterceptor(ctInterceptor)
            .addInterceptor(authentionInterceptor)
            .cache(null)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor):HttpLoggingInterceptor{
        val interceptor=HttpLoggingInterceptor(loggingInterceptor)

        interceptor.level=HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}