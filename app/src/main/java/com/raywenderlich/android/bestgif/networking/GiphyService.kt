package com.raywenderlich.android.bestgif.networking

import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GiphyService {
  companion object {
    fun create(): GiphyService {
      val logging = HttpLoggingInterceptor()
      logging.level = HttpLoggingInterceptor.Level.BODY

      val client = OkHttpClient.Builder()
          .addInterceptor(logging)
          .readTimeout(20, TimeUnit.SECONDS)
          .build()

      val retrofit = Retrofit.Builder()
          .baseUrl(GiphyApi.API)
          .client(client)
          .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .build()

      return retrofit.create(GiphyService::class.java)
    }
  }

  @GET("gifs/search")
  fun getGifs(
      @Query("api_key") apiKey: String,
      @Query("q") searchParam: String
  ): Single<GiphyResponse>
}
