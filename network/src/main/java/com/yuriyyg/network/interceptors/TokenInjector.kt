package com.yuriyyg.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

//originalRequest мы не можем редактировать, поэтому мы получили копию данного обьекта в authRequest


@Singleton
class TokenInjector @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()


        //берем копию с помощью  newBuilder()
        val authRequest = originalRequest
            .newBuilder()
            .method(originalRequest.method, originalRequest.body)


        authRequest.addHeader("AUTHORIZATION", "Bearer{token}")

        authRequest.addHeader("apiKey", "123123123")
        authRequest.addHeader("X_Mobile", "android")
        authRequest.addHeader("X_Mobile", "ios")

        return chain.proceed(authRequest.build())
    }


}