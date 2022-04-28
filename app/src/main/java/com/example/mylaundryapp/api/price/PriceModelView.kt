package com.example.mylaundryapp.api.price

import com.google.gson.annotations.SerializedName

data class PriceModelView(

    @field:SerializedName("is_packet")
    val isPacket: Boolean? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("price_time")
    val priceTime: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("price_title")
    val priceTitle: String? = null,
)
