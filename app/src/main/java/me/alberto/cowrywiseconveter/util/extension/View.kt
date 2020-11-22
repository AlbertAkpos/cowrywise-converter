package me.alberto.cowrywiseconveter.util.extension

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import me.alberto.cowrywiseconveter.R

@BindingAdapter("app:symbols")
fun AutoCompleteTextView.setSymbols(list: List<String>?) {
    val adapter = ArrayAdapter(context, R.layout.simple_text_item, list ?: emptyList())
    setAdapter(adapter)
}