package com.mobiapps.courses.tmdb.datasources

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// https://api.themoviedb.org/3/movie/now_playing?api_key=<<api_key>>&language=en-US&page=1

object ApiClient {
    private const val BASE_URL: String = "https://api.themoviedb.org/3/"

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(Interceptor {
                val original: Request = it.request()
                val originalHttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "48d02d2803f669be5643367e3307dd43")
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                return@Interceptor it.proceed(request)
            })
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val tmdbService: TmdbService by lazy {
        retrofit.create(TmdbService::class.java)
    }
}