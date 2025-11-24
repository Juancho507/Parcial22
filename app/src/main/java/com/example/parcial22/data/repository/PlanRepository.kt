package com.example.parcial22.data.repository

import com.example.parcial22.data.model.Member
import com.example.parcial22.data.model.Payment
import com.example.parcial22.data.model.Plan
import com.example.parcial22.data.remote.ApiService

class PlanRepository(private val api: ApiService) {

    suspend fun getPlans() = api.getPlans()
    suspend fun getMembers(planId: String) = api.getMembersByPlan(planId)
    suspend fun getPayments(planId: String) = api.getPaymentsByPlan(planId)

    suspend fun addMember(member: Member) = api.createMember(member)
    suspend fun addPayment(payment: Payment) = api.createPayment(payment)
    suspend fun createPlan(plan: Plan) = api.createPlan(plan)


}
