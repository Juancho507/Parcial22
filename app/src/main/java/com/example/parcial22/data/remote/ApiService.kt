package com.example.parcial22.data.remote

import com.example.parcial22.data.model.*
import retrofit2.http.*

interface ApiService {

    @GET("api/plans")
    suspend fun getPlans(): List<Plan>

    @GET("api/members/plan/{planId}")
    suspend fun getMembersByPlan(@Path("planId") planId: String): List<Member>

    @GET("api/payments/plan/{planId}")
    suspend fun getPaymentsByPlan(@Path("planId") planId: String): List<Payment>

    @POST("api/members")
    suspend fun createMember(@Body member: Member): Member

    @POST("api/payments")
    suspend fun createPayment(@Body payment: Payment): Payment

    @POST("api/plans")
    suspend fun createPlan(@Body plan: Plan): Plan
}

