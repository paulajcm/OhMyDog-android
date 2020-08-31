package com.github.paulajcm.ohmydog.android.di

import com.github.paulajcm.ohmydog.android.AppConstants
import com.github.paulajcm.ohmydog.android.AppConstants.DOG_API_URL
import com.github.paulajcm.ohmydog.android.network.DogApi
import com.github.paulajcm.ohmydog.android.network.DogService
import com.github.paulajcm.ohmydog.android.repository.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DogModule {
    private val authInterceptor =
        Interceptor { chain: Interceptor.Chain ->
            val newUrl = chain.request().url()
            chain.request().url()
                .newBuilder()
                .addQueryParameter("x_api_key", AppConstants.API_KEY)
                .build()
            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }
    private val dogApiClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(dogApiClient)
        .baseUrl(DOG_API_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideDogApi(): DogApi {
        return retrofit.create(DogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDogService(dogApi: DogApi): DogService {
        return DogService(dogApi)
    }

    @Provides
    @Singleton
    fun provideDogRepository(dogService: DogService): DogRepository {
        return DogRepository(dogService)
    }
}