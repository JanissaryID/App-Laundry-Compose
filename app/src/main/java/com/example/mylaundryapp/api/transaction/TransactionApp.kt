package com.example.mylaundryapp.api.transaction

import android.util.Log
import com.example.mylaundryapp.IP_ADDRESS
import com.example.mylaundryapp.api.machine.MachineApp
import com.example.mylaundryapp.api.machine.MachineService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TransactionApp {
    private var BASE_URL = "http://$IP_ADDRESS:8000"
//    private var BASE_URL = "http://192.168.112.244:8000"

    fun CreateInstance(): TransactionService {
//        Log.d("debug", "IP in Price : $IP_ADDRESS")
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(TransactionService::class.java)
    }
}