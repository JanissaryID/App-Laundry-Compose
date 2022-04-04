package com.example.mylaundryapp.api.payment.responseqris

import com.google.gson.annotations.SerializedName

data class ResponsePaymentQrisModel(

    @field:SerializedName("refid")
    val refid: Long? = null
)