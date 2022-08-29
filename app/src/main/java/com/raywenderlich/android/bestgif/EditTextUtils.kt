package com.raywenderlich.android.bestgif

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.rxjava3.core.Observable

fun EditText.textChanges(): Observable<String> {


    return Observable.create { emitter ->
        val textWatcher = object : TextWatcher {

            override fun afterTextChanged(text: Editable) {
                emitter.onNext(text.toString())
            }

            override fun beforeTextChanged(
                p0: CharSequence?, p1: Int, p2: Int, p3: Int
            ) {
            }

            override fun onTextChanged(
                p0: CharSequence?, p1: Int, p2: Int, p3: Int
            ) {
            }
        }

        addTextChangedListener (textWatcher)

        emitter.setCancellable {
            removeTextChangedListener(textWatcher)
        }
    }
}
