package com.example.mylaundryapp.api.transaction

import com.example.mylaundryapp.KEY_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TransactionApp {
//    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/9f3304b4-abc8-424e-8f73-f305ef4ac099/"
    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/$KEY_URL/"
//    private var BASE_URL = "http://$IP_ADDRESS:8000"
//    private var BASE_URL = "http://192.168.112.244:8000"

    fun CreateInstance(): TransactionService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(TransactionService::class.java)
    }
}