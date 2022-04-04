package com.example.mylaundryapp.api.price

import com.example.mylaundryapp.api.transaction.TransactionModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PriceService {
    @GET("/fetch-price")
    fun fetchPrice(): Call<List<PriceModel>>
}