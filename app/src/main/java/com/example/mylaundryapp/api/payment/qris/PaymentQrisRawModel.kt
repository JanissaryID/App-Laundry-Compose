package com.example.mylaundryapp.api.payment.qris

import com.google.gson.annotations.SerializedName

data class PaymentQrisRawModel(

    @field:SerializedName("refid")
    val refid: Long? = null,

    @field:SerializedName("rawqr")
    val rawqr: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)