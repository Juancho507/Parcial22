package com.example.parcial22.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.parcial22.data.model.*
import com.example.parcial22.data.repository.PlanRepository
import kotlinx.coroutines.launch

class PlanViewModel(private val repo: PlanRepository) : ViewModel() {

    private val _planes = MutableLiveData<List<Plan>>()
    val planes: LiveData<List<Plan>> = _planes

    fun loadPlanes() {
        viewModelScope.launch {
            try {
                _planes.value = repo.getPlans()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private val _members = MutableLiveData<List<Member>>()
    val members: LiveData<List<Member>> = _members

    private val _memberCreated = MutableLiveData<Boolean>()
    val memberCreated: LiveData<Boolean> = _memberCreated

    fun loadMembers(planId: String) {
        viewModelScope.launch {
            try {
                _members.value = repo.getMembers(planId)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun createMember(name: String, planId: String, contribution: Int = 0) {
        viewModelScope.launch {
            try {
                repo.addMember(
                    Member(
                        id = null,
                        name = name,
                        planId = planId,
                        contributionPerMonth = contribution
                    )
                )
                _memberCreated.value = true
                loadMembers(planId)
            } catch (e: Exception) {
                _memberCreated.value = false
            }
        }
    }

    fun addMemberToPlan(name: String, planId: String, contributionPerMonth: Int) {
        viewModelScope.launch {
            repo.addMember(
                Member(
                    id = null,
                    name = name,
                    planId = planId,
                    contributionPerMonth = contributionPerMonth
                )
            )
            loadMembers(planId)
        }
    }

    private val _payments = MutableLiveData<List<Payment>>()
    val payments: LiveData<List<Payment>> = _payments

    fun loadPayments(planId: String) {
        viewModelScope.launch {
            try {
                _payments.value = repo.getPayments(planId)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun addPaymentToPlan(amount: Int, planId: String, memberId: String) {
        viewModelScope.launch {
            repo.addPayment(
                Payment(
                    id = null,
                    amount = amount,
                    planId = planId,
                    memberId = memberId
                )
            )
            loadPayments(planId)
        }
    }

    fun createPlan(name: String, motive: String, targetAmount: Int, months: Int) {
        viewModelScope.launch {
            repo.createPlan(
                Plan(
                    id = null,
                    name = name,
                    motive = motive,
                    targetAmount = targetAmount,
                    months = months
                )
            )
            loadPlanes()
        }
    }
}
