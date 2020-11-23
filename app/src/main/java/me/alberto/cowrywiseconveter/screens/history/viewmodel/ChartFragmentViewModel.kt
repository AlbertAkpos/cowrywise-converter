package me.alberto.cowrywiseconveter.screens.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.alberto.cowrywiseconveter.data.domain.usecase.GetRateHistoryUserCase
import me.alberto.cowrywiseconveter.data.remote.model.HistoryQuery
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.util.DateFormatter
import javax.inject.Inject

class ChartFragmentViewModel @Inject constructor(private val getRateHistoryUserCase: GetRateHistoryUserCase) :
    ViewModel() {
    fun getRateHistory(query: Query, historyToFetch: Int) {
        viewModelScope.launch {
            try {
                val daysAgo = if (historyToFetch == 1){ 30} else{ 90 }
                val startDate = DateFormatter.getDaysAgo(daysAgo)
                val endDate = DateFormatter.getCurrentDate()
                val historyQuery = HistoryQuery(startDate, endDate, query.from, query.to)
                getRateHistoryUserCase.execute(historyQuery)
            } catch (exp: Exception) {
                exp.printStackTrace()
                //TODO Do show the error
            }

        }
    }
}