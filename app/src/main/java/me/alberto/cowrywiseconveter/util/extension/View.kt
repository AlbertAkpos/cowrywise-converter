package me.alberto.cowrywiseconveter.util.extension

import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import me.alberto.cowrywiseconveter.data.domain.model.Country
import me.alberto.cowrywiseconveter.screens.home.adapter.CountryAdapter

@BindingAdapter("app:symbols")
fun AutoCompleteTextView.setSymbols(list: List<Country>?) {
    val adapter = CountryAdapter(context, list ?: emptyList())
    setAdapter(adapter)
    adapter.notifyDataSetChanged()
}