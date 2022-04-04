package com.example.mylaundryapp.api.price

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mylaundryapp.IP_ADDRESS
import com.example.mylaundryapp.api.transaction.TransactionService
import com.example.mylaundryapp.room.setting.SettingDao
import com.example.mylaundryapp.room.setting.SettingItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PriceApp {
    private var BASE_URL = "http://$IP_ADDRESS:8000"
//    private var BASE_URL = "http://192.168.112.244:8000"

    fun CreateInstance(): PriceService{
//        Log.d("debug", "IP in Price : $IP_ADDRESS")
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

