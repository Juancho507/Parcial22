package com.example.parcial22.data.model

import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("_id") val id: String?,
    @SerializedName("planId") val planId: String,
    @SerializedName("memberId") val memberId: String,
    @SerializedName("amount") val amount: Int,
    @SerializedName("date") val date: String?=null
)