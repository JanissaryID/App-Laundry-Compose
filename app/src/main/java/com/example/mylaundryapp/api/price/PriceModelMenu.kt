package com.example.mylaundryapp.api.price

import com.google.gson.annotations.SerializedName

data class PriceModelMenu(
    @field:SerializedName("price_type_menu")
    val priceTypeMenu: String? = null
)
