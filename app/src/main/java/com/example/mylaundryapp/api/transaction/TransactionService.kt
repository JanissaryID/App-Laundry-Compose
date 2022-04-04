package com.example.mylaundryapp.api.transaction

import retrofit2.Call
import retrofit2.http.*

interface TransactionService {
    @GET("/fetch-transactions")
    fun getTransactions(): Call<List<TransactionModel>>

    @GET("/fetch-transactions-filter-date")
    fun getFilterTransactions(@Query(value="date", encoded=true) date: String?): Call<List<TransactionModel>>

    @POST("/add-transaction")
    fun insertTransactions(@Body statusData: TransactionModel) : Call<TransactionModel>

}