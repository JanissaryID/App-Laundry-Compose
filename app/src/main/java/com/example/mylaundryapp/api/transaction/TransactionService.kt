package com.example.mylaundryapp.api.transaction

import com.example.mylaundryapp.api.machine.MachineModelUpdate
import retrofit2.Call
import retrofit2.http.*

interface TransactionService {
    @GET("/fetch-transactions")
    fun getTransactions(): Call<List<TransactionModel>>

    @PUT("/update-transaction")
    fun putTransaction(
        @Query(value="id", encoded=true) id: Int?, @Body updateData : TransactionModel
    ): Call<TransactionModel>

//    @GET("/update-transaction")
//    fun updateTransaction(@Query(value="id", encoded=true) id: Int?): Call<List<TransactionModel>>

    @GET("/fetch-transactions-filter-finish-date")
    fun getFilterTransactionsFinishDate(
        @Query(value="date", encoded=true) date: String?,
        @Query(value="finish", encoded=true) finish: Boolean?
    ): Call<List<TransactionModel>>

    @GET("/fetch-transactions-filter-finish")
    fun getFilterTransactionsFinish(
        @Query(value="finish", encoded=true) finish: Boolean?
    ): Call<List<TransactionModel>>

    @POST("/add-transaction")
    fun insertTransactions(@Body statusData: TransactionModel) : Call<TransactionModel>

}