package me.alberto.cowrywiseconveter.data.domain.usecase

abstract class UseCase<T, PARAMS> protected constructor() {
    protected abstract suspend fun buildUseCase(params: PARAMS?): T
    suspend fun execute(params: PARAMS? = null) = buildUseCase(params)
}

//For live data not requiring a suspend function
abstract class UniqueUseCase<T, PARAMS> protected constructor() {
    protected abstract fun buildUseCase(params: PARAMS?): T
    fun execute(params: PARAMS ? =null): T = buildUseCase(params)
}