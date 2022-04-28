package com.example.mylaundryapp.api.transaction

import com.example.mylaundryapp.api.machine.MachineModelUpdate
import retrofit2.Call
import retrofit2.http.*

interface TransactionService {
    @GET("Transaction")
    fun getTransactions(): Call<List<TransactionModel>>

    @PUT("/update-transaction")
    fun putTransaction(
        @Query(value="id", encoded=true) id: String?, @Body updateData : TransactionModel
    ): Call<TransactionModel>

//    @GET("/update-transaction")
//    fun updateTransaction(@Query(value="id", encoded=true) id: Int?): Call<List<TransactionModel>>

    @GET("Transaction")
    fun getFilterTransactionsFinishDate(
        @Query(value="transaction_date", encoded=true) date: String?,
        @Query(value="transaction_finish", encoded=true) finish: Boolean?
    ): Call<List<TransactionModel>>

    @GET("Transaction")
    fun getFilterTransactionsFinish(
        @Query(value="transaction_finish", encoded=true) finish: Boolean?
    ): Call<List<TransactionModel>>

    @POST("Transaction")
    fun insertTransactions(@Body statusData: TransactionModel) : Call<TransactionModel>

}