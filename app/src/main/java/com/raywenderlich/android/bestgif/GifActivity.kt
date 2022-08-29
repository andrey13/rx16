package com.raywenderlich.android.bestgif

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.android.bestgif.networking.GiphyApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_gif.*

class GifActivity : AppCompatActivity() {
  private val adapter = GiphyAdapter()
  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_gif)

    list.layoutManager = LinearLayoutManager(this)
    list.adapter = adapter

    text_input
      .textChanges()
      .flatMapSingle { GiphyApi.searchForGifs(it) }
      .onErrorReturnItem(listOf(GiphyGif(
        "https://media.giphy.com/media/SQ24FpNRW9yRG/giphy.gif")))
      .subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())
      .subscribe { adapter.items = it }
      .addTo(disposables)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.dispose()
  }
}
