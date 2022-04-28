package com.example.mylaundryapp.api.machine

import com.example.mylaundryapp.KEY_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MachineApp {
//    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/9f3304b4-abc8-424e-8f73-f305ef4ac099/"
    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/$KEY_URL/"

    fun CreateInstance(): MachineService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(MachineService::class.java)
    }

//    val INSTANCE : MachineService by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        retrofit.create(MachineService::class.java)
//    }
}