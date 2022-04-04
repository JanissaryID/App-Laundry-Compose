package com.example.mylaundryapp.api.payment

import com.example.mylaundryapp.api.price.PriceApp
import com.example.mylaundryapp.api.price.PriceService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PaymentQrisApp {
    //    private var BASE_URL = "http://${LIST_VALUE_ROOM_SETTING_GET[0].valueSetting}:8000"
    private const val BASE_URL = "https://api.paydia.id"

    var INSTANCE: PaymentQrisService? = null

    fun CreateInstance(): PaymentQrisService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        INSTANCE = retrofit.create(PaymentQrisService::class.java)
        return INSTANCE as PaymentQrisService
    }

    fun DestroyInstance(){
        INSTANCE = null
    }
}