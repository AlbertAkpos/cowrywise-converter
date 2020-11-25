package me.alberto.cowrywiseconveter.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule

abstract class BaseTest {
    @ExperimentalCoroutinesApi
    protected val testDispatcher = TestCoroutineDispatcher()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

}