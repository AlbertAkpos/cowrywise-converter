package me.alberto.cowrywiseconveter.screens.history.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.alberto.cowrywiseconveter.data.domain.usecase.GetRateHistoryUserCase
import me.alberto.cowrywiseconveter.data.remote.model.HistoryQuery
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.data.remote.source.Result
import me.alberto.cowrywiseconveter.util.DateFormatter
import javax.inject.Inject

class ChartFragmentViewModel @Inject constructor(private val getRateHistoryUserCase: GetRateHistoryUserCase) :
    ViewModel() {
    private val _rates = MutableLiveData<Map<String, Double>>()
    val rates: LiveData<Map<String, Double>> = _rates

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> = _loading


    fun getRateHistory(query: Query, historyToFetch: Int) {
        viewModelScope.launch {
            try {
                _loading.postValue(true)
                val daysAgo = if (historyToFetch == 1) { 30 } else { 90 }
                val startDate = DateFormatter.getDaysAgo(daysAgo)
                val endDate = DateFormatter.getCurrentDate()
                val historyQuery = HistoryQuery(startDate, endDate, query.from, query.to)
                when (val result = getRateHistoryUserCase.execute(historyQuery)) {
                    is Result.Success -> {
                        _rates.value = result.data
                        _loading.postValue(false)
                    }
                    is Result.Failure -> {
                        _error.value = result.error
                        _loading.postValue(false)
                    }
                }
            } catch (exp: Exception) {
                exp.printStackTrace()
                _loading.postValue(false)
                _error.postValue("Some error occurred. Please try again")
                //TODO Do show the error
            }

        }
    }
}