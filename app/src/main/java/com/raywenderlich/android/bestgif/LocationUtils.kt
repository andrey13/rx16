package com.raywenderlich.android.bestgif

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import io.reactivex.rxjava3.core.Observable
import java.util.*


@SuppressLint("MissingPermission")
fun locationUpdates(context: Context): Observable<Location> {
 return Observable.empty()
}

fun cityFromLocation(context: Context, location: Location): String {
  val gcd = Geocoder(context, Locale.getDefault())
  val addresses = gcd.getFromLocation(location.latitude, location.longitude, 1)
  return if (addresses.size > 0) {
    addresses[0].locality
  } else {
    ""
  }
}
