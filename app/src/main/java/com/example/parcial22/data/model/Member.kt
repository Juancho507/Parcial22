package com.example.parcial22.data.model

import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("_id") val id: String?,
    @SerializedName("name") val name: String,
    @SerializedName("planId") val planId: String,
    @SerializedName("contributionPerMonth") val contributionPerMonth: Int
)