package com.example.mylaundryapp.api.transaction

import com.google.gson.annotations.SerializedName

data class TransactionModel(

	@field:SerializedName("transaction_date")
	val transactionDate: String? = null,

	@field:SerializedName("transaction_class_machine")
	val transactionClassMachine: String? = null,

	@field:SerializedName("transaction_menu_machine")
	val transactionMenuMachine: String? = null,

	@field:SerializedName("transaction_price")
	val transactionPrice: String? = null,

	@field:SerializedName("transaction_finish")
	val transactionFinish: Boolean? = null,

	@field:SerializedName("transaction_store")
	val transactionStore: Int? = null,

	@field:SerializedName("transaction_type_menu")
	val transactionTypeMenu: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("transaction_number_machine")
	val transactionNumberMachine: Int? = null,

	@field:SerializedName("transaction_type_payment")
	val transactionTypePayment: String? = null
)
