package me.alberto.cowrywiseconveter.data.repository

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import me.alberto.cowrywiseconveter.base.BaseTest
import me.alberto.cowrywiseconveter.data.remote.source.Result
import me.alberto.cowrywiseconveter.mockkdata.*
import me.alberto.cowrywiseconveter.util.getOrAwaitValue
import org.junit.After
import org.junit.Test

class RepositoryTest : BaseTest() {
    private val remoteSource = FakeRemoteSource()
    private val localSource = FakeLocalDataSource()
    private val repository by lazy { Repository(remoteSource, localSource) }

    @After
    fun tearDown() {
        database.clear()
    }

    @Test
    fun `returns correct list of country when database is empty`() {
        val symbols = repository.getSymbols()
        assertEquals(0, symbols.getOrAwaitValue().size)
    }

    @Test
    fun `returns size of 2 when two countries are added to database`() {
        runBlocking {
            repository.getSymbolsFromRemote()
            assertEquals(2, database.size)
        }
    }

    @Test
    fun `returns correct size when database is populated`() {
        val countries = populateDatabase()
        runBlocking {
            localSource.addCountry(countries)
            assertEquals(countries.size, repository.getSymbols().getOrAwaitValue().size)
        }

    }

    @Test
    fun `returns Success for conversion from USD to  NGN`() {
        val amount = 23.0
        runBlocking {
            val result = repository.convert(getUSD(), getNGN(), amount)
            val success = result is Result.Success
            assertEquals(true, success)
        }
    }

    @Test
    fun `returns correct conversion result from USB to NGN`() {
        val amount = 50.0
        runBlocking {
            val result = repository.convert(getUSD(), getNGN(), amount)
            result as Result.Success
            assertEquals(amount * getNairaValue(), result.data )
        }
    }

}