package com.example.mylaundryapp.api.price

import android.util.Log
import com.example.mylaundryapp.KEY_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PriceApp {
//    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/9f3304b4-abc8-424e-8f73-f305ef4ac099/"
    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/$KEY_URL/"
//    private var BASE_URL = "http://192.168.112.244:8000"

    fun CreateInstance(): PriceService{
        Log.d("debug", "KEY in Price : $KEY_URL")
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(PriceService::class.java)
    }

//    val INSTANCE : PriceService by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        Log.d("debug", "IP in Price : $IP_ADDRESS")
//
//        retrofit.create(PriceService::class.java)
//    }
}

