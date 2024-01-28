package com.yuriyyg.network.di

import com.google.gson.Gson
import com.yuriyyg.network.api.ApiService
import com.yuriyyg.network.interceptors.TokenInjector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataLayerModule {

    @Provides
    @Singleton
    fun provideGson():Gson{
        return Gson()
    }

    @Provides
    @Singleton
    @FlightAnnotation
    fun provideApiClient(gson: Gson, @FlightAnnotation client: OkHttpClient): Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://65a7624794c2c5762da692dd.mockapi.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        return retrofit
    }


    @Provides
    @Singleton
    fun provideApiService(@FlightAnnotation retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @FlightAnnotation
    fun provideOkHttpClient(tokenInjector: TokenInjector): OkHttpClient {
        val client = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY  // интерсептор для логирования


        client
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInjector)
        return client.build()
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME) // если тут создается два и более синглтон ретрофита или
annotation class FlightAnnotation       // okhttp то нужно создать собственные аннотации и пометить их
                                        // иначе выйдет ошибка (и не важно они в одном классе или в
                                        // разных, так как тут используется даггер хилт)