package me.alberto.cowrywiseconveter.screens.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.alberto.cowrywiseconveter.data.domain.usecase.ConvertUserCase
import me.alberto.cowrywiseconveter.data.domain.usecase.GetSymbolsUserCase
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.data.remote.source.Result
import me.alberto.cowrywiseconveter.util.LoadingState
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getSymbolsUserCase: GetSymbolsUserCase,
    private val convertUserCase: ConvertUserCase
) :
    ViewModel() {

    private val _symbols = MutableLiveData<List<String>>()
    val symbols: LiveData<List<String>> = _symbols
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    val convertText = MutableLiveData<String>()
    val conversionResult = MutableLiveData<String>()

    val baseCurrency = MutableLiveData<String>()
    val targetCurrency = MutableLiveData<String>()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getSymbols()
    }

    fun getSymbols() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.Loading
                when (val result = getSymbolsUserCase.execute()) {
                    is Result.Success -> {
                        _symbols.value = result.data
                        _loadingState.value = LoadingState.Success
                    }
                    is Result.Failure -> {
                        _error.value = result.error
                        _loadingState.value = LoadingState.Error(result.error)
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                _error.value = exception.message
                _loadingState.value = LoadingState.Error("Network error. Please try again")
            }
        }
    }

    fun convert() {
        if (convertText.value.isNullOrEmpty()) {
            _error.postValue("Value empty")
        }

        if (baseCurrency.value.isNullOrEmpty()) {

        }

        if (targetCurrency.value.isNullOrEmpty()) {

        }

        if (!convertText.value.isNullOrEmpty() && !baseCurrency.value.isNullOrEmpty() && !targetCurrency.value.isNullOrEmpty()) {
            viewModelScope.launch {
                try {
                    val query = Query(
                        baseCurrency.value.toString(),
                        targetCurrency.value.toString(),
                        convertText.value!!.toDouble()
                    )
                    when (val result = convertUserCase.execute(query)) {
                        is Result.Success -> conversionResult.value = result.data.toString()
                        is Result.Failure -> _error.value = result.error
                    }

                } catch (exp: Exception) {
                    _error.value = exp.message
                }

            }

        }

    }

}