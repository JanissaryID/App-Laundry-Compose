package com.example.mylaundryapp.api.price

import com.google.gson.annotations.SerializedName

data class PriceModel(

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("price_time")
	val priceTime: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("price_class_machine")
	val priceClassMachine: Boolean? = null,

	@field:SerializedName("price_title")
	val priceTitle: String? = null,

	@field:SerializedName("price_type_menu")
	val priceTypeMenu: String? = null,

	@field:SerializedName("price_store")
	val priceStore: Int? = null
)
