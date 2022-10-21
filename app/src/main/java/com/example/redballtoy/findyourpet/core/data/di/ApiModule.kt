package com.example.redballtoy.findyourpet.core.data.di

import com.appmattus.certificatetransparency.certificateTransparencyInterceptor
import com.example.redballtoy.findyourpet.core.data.api.ApiConstants
import com.example.redballtoy.findyourpet.core.data.api.PetFinderApi
import com.example.redballtoy.findyourpet.core.data.api.interceptors.AuthenticationInterceptor
import com.example.redballtoy.findyourpet.core.data.api.interceptors.LoggingInterceptor
import com.example.redballtoy.findyourpet.core.data.api.interceptors.NetworkStatusInterceptor
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
        networkStatusInterceptor: NetworkStatusInterceptor,
        authentionInterceptor: AuthenticationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkStatusInterceptor)
            .addInterceptor(authentionInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor):HttpLoggingInterceptor{
        val interceptor=HttpLoggingInterceptor(loggingInterceptor)

        interceptor.level=HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}