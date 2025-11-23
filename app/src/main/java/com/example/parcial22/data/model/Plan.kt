package com.example.parcial22.data.model

import com.google.gson.annotations.SerializedName

data class Plan(
    @SerializedName("_id") val id: String?,
    @SerializedName("name") val name: String,
    @SerializedName("motive") val motive: String,
    @SerializedName("targetAmount") val targetAmount: Int,
    @SerializedName("months") val months: Int
)