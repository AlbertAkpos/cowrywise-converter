package me.alberto.cowrywiseconveter.util.extension

import me.alberto.cowrywiseconveter.data.domain.model.Country

fun List<String>.toCountryList(): List<Country> {
    return map { Country(it, it) }
}