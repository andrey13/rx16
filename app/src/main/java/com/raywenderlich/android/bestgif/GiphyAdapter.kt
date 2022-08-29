package com.raywenderlich.android.bestgif

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.bestgif.imageloading.GlideApp
import kotlinx.android.synthetic.main.adapter_gif.view.*

class GiphyAdapter : RecyclerView.Adapter<GiphyViewHolder>() {
  var items: List<GiphyGif> = emptyList()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_gif, parent, false)
    return GiphyViewHolder(view)
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
    val item = items[position]
    val imageView = holder.itemView.image_view
    GlideApp.with(imageView)
      .load(item.url)
      .into(imageView)
  }
}
