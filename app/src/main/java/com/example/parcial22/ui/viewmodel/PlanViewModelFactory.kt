package com.example.parcial22.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial22.data.repository.PlanRepository
import kotlin.jvm.java

class PlanViewModelFactory(private val repository: PlanRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlanViewModel(repository) as T
        }
        throw kotlin.IllegalArgumentException("Unknown ViewModel class")
    }
}