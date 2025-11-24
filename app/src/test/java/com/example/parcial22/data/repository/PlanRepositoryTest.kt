package com.example.parcial22.data.repository

import com.example.parcial22.data.model.Plan
import com.example.parcial22.data.remote.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PlanRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var planRepository: PlanRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        planRepository = PlanRepository(apiService)
    }

    @Test
    fun getPlans_returnsPlans() = runBlocking {
        // Given
        val expectedPlans = listOf(
            Plan("1", "Plan 1", "Motive 1", 100, 1),
            Plan("2", "Plan 2", "Motive 2", 200, 2)
        )
        `when`(apiService.getPlans()).thenReturn(expectedPlans)

        // When
        val result = planRepository.getPlans()

        // Then
        assertEquals(expectedPlans, result)
    }
}