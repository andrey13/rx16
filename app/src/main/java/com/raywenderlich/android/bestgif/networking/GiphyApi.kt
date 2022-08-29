package com.raywenderlich.android.bestgif.networking

import com.raywenderlich.android.bestgif.GiphyGif
import io.reactivex.rxjava3.core.Single

object GiphyApi {
  const val API = "http://api.giphy.com/v1/"
  private const val API_KEY = "cakHyL8G0TbrHB0YH7U4VWA5YPGR0pCj"
  private val giphyService = GiphyService.create()

  fun searchForGifs(searchTerm: String): Single<List<GiphyGif>> {
    return giphyService.getGifs(API_KEY, searchTerm)
      .map { giphyResponse -> giphyResponse.data.map { it.images.fixed_height } }
      .onErrorReturn { listOf() }
  }
}
